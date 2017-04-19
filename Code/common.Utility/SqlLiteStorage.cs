using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SQLite;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace common.Utility
{
    public abstract class SqlLiteStorage
    {
        protected static string CacheFolderPath
        {
            get
            {
                if (!Directory.Exists(Path.Combine(LocalDataPath, "cache")))
                {
                    Directory.CreateDirectory(Path.Combine(LocalDataPath, "cache"));
                }
                return Path.Combine(LocalDataPath, "cache");
            }
        }

        private static string _localDataPath;
        /// <summary>
        /// windows的LocalApplicationData中存储软件配置信息的子目录路径
        /// </summary>
        protected static string LocalDataPath
        {
            get
            {
                if (string.IsNullOrEmpty(_localDataPath))
                {
                    try
                    {
                        string subFolderFile = Path.Combine("folder.txt");
                        string subFloder = null;
                        if (File.Exists(subFolderFile))
                        {
                            subFloder = File.ReadAllText(subFolderFile);
                        }
                        if (string.IsNullOrEmpty(subFloder))
                        {
                            subFloder = Guid.NewGuid().ToString();
                            File.WriteAllText(subFolderFile, subFloder);
                        }
                        _localDataPath = Path.Combine(Path.Combine(System.Environment.GetFolderPath(Environment.SpecialFolder.LocalApplicationData), "MySoft"), subFloder);
                        if (!Directory.Exists(_localDataPath))
                            Directory.CreateDirectory(_localDataPath);
                    }
                    catch (Exception ex)
                    {
                        LogHelper.LogHelper.Log(ex);
                        _localDataPath = Environment.CurrentDirectory;
                    }
                }
                return _localDataPath;
            }
        }

        public SqlLiteStorage()
        {
            Initialize();
        }

        public void Initialize()
        {
            try
            {
                InitializeDB();
            }
            catch
            {
                //可能数据库文件损坏，尝试删除文件并重建数据库
                File.Delete(GetDatabaseFile());
                InitializeDB();
            }
        }

        private void InitializeDB()
        {
            //创建数据库文件并设置密码
            if (!File.Exists(GetDatabaseFile()))
            {
                SQLiteConnection.CreateFile(GetDatabaseFile());
                using (SQLiteConnection conn = new SQLiteConnection(string.Format("Data Source={0};Version=3;", GetDatabaseFile())))
                {
                    conn.Open();
                    conn.ChangePassword("skieer@2014");
                }
            }
            //创建数据表
            CreatTable();
        }

        protected virtual string GetDatabaseFile()
        {
            return Path.Combine(CacheFolderPath, "octopuscachev3.data");
        }

        /// <summary>
        /// 判断表是否存在，如不存在则创建表
        /// </summary>
        /// <param name="tableName"></param>
        protected abstract void CreatTable();

        protected IDbConnection GetConnection()
        {
            string dbConnStr = string.Format("Data Source={0};Version=3; Password=skieer@2014", GetDatabaseFile());
            return new SQLiteConnection(dbConnStr);
        }

        public abstract string GetCreateTableCmd();

        private void TryCreateTable(IDbCommand cmd)
        {
            cmd.CommandText = GetCreateTableCmd();
            cmd.ExecuteNonQuery();
        }

        public int ExecuteNonQuery(string cmdStr, List<IDbDataParameter> parameters)
        {
            int res = 0;
            if (string.IsNullOrEmpty(cmdStr) == false)
            {
                using (IDbConnection conn = GetConnection())
                {
                    conn.Open();
                    using (IDbCommand cmd = conn.CreateCommand())
                    {
                        TryCreateTable(cmd);
                        foreach (var p in parameters)
                        {
                            cmd.Parameters.Add(p);
                        }
                        cmd.CommandText = cmdStr;
                        res = cmd.ExecuteNonQuery();
                    }
                }
            }
            return res;
        }

        public IDataReader ExecuteQuery(IDbConnection conn, string cmdStr, List<IDbDataParameter> parameters)
        {
            if (string.IsNullOrEmpty(cmdStr) == false)
            {
                conn.Open();
                IDbCommand cmd = conn.CreateCommand();
                TryCreateTable(cmd);
                foreach (var p in parameters)
                {
                    cmd.Parameters.Add(p);
                }
                cmd.CommandText = cmdStr;
                return cmd.ExecuteReader();
            }
            return null;
        }

        protected IDbDataParameter CreateParameter(string name, object value)
        {
            return new SQLiteParameter(name, value);
        }
    }

}
