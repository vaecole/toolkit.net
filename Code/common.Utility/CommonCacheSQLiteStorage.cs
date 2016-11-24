using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Data.SQLite;
using System.Data;
using System.IO;

namespace common.Utility
{
    /// <summary>
    /// 提供一个存储Name:Object名称对象对到SQLite的类，使用时需要确保输入和输出时的类型一致性
    /// </summary>
    public class CommonCacheSQLiteStorage : SqlLiteStorage
    {
        private string createTableCMD = @"CREATE TABLE IF NOT EXISTS [CommonCache]([Name] TEXT NOT NULL PRIMARY KEY,[Data] TEXT NOT NULL);";
        protected override void CreatTable()
        {
            using (IDbConnection conn = GetConnection())
            {
                conn.Open();
                using (IDbCommand cmd = conn.CreateCommand())
                {
                    cmd.CommandText = GetCreateTableCmd();
                    cmd.ExecuteNonQuery();
                }
            }
        }
        protected override string GetDatabaseFile()
        {
            return Path.Combine(CacheFolderPath, "octopusCommonCache.data");
        }
        public override string GetCreateTableCmd()
        {
            return createTableCMD;
        }

        public bool Insert(string Name, object Data)
        {
            bool res = false;
            string cmdStr = @"INSERT INTO [CommonCache] (Name,Data)
                              VALUES(@Name,@Data);";
            List<IDbDataParameter> paras = new List<IDbDataParameter>{
                        CreateParameter("Name",Name),
                        CreateParameter("Data",Newtonsoft.Json.JsonConvert.SerializeObject(Data)),
                };
            res = ExecuteNonQuery(cmdStr, paras) > 0;
            return res;
        }

        public bool Update(string Name, object Data)
        {
            bool res = false;
            string cmdStr = @"UPDATE [CommonCache] SET Data=@Data
                              WHERE Name=@Name;";
            List<IDbDataParameter> paras = new List<IDbDataParameter>{
                        CreateParameter("Name",Name),
                        CreateParameter("Data",Newtonsoft.Json.JsonConvert.SerializeObject(Data)),
                };
            res = ExecuteNonQuery(cmdStr, paras) > 0;
            return res;
        }

        public bool HasSQLiteRecord(string Name)
        {
            bool res = false;
            string cmdStr = @"SELECT Name FROM [CommonCache] WHERE Name = @Name;";
            List<IDbDataParameter> paras = new List<IDbDataParameter> { CreateParameter("Name", Name) };
            using (IDbConnection conn = GetConnection())
            {
                using (IDataReader reader = ExecuteQuery(conn, cmdStr, paras))
                {
                    res = reader.Read();
                }
                conn.Close();
            }
            return res;
        }

        public void DeleteAll()
        {
            string cmdStr = @"DELETE FROM [CommonCache];";
            ExecuteNonQuery(cmdStr, null);
        }

        public void Delete(string Name)
        {
            string cmdStr = @"DELETE FROM [CommonCache] WHERE Name =@Name;";
            List<IDbDataParameter> paras = new List<IDbDataParameter> { CreateParameter("Name", Name) };
            ExecuteNonQuery(cmdStr, paras);
        }

        public T Select<T>(string Name)
        {
            T res = default(T);
            string cmdStr = @"SELECT Data FROM [CommonCache] WHERE Name = @Name;";
            List<IDbDataParameter> paras = new List<IDbDataParameter> { CreateParameter("Name", Name) };
            using (IDbConnection conn = GetConnection())
            {
                using (IDataReader reader = ExecuteQuery(conn, cmdStr, paras))
                {
                    if (reader.Read())
                    {
                        res = (T)Newtonsoft.Json.JsonConvert.DeserializeObject(reader["Data"].ToString(), typeof(T));
                    }
                }
                conn.Close();
            }
            return res;
        }

        public void Save(string Name, object Data)
        {
            if (HasSQLiteRecord(Name))
                Update(Name, Data);
            else
                Insert(Name, Data);
        }
    }
}
