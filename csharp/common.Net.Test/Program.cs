using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using common.Net;
using common.Utility;
namespace common.Net.Test
{
    class Program
    {
        static void Main(string[] args)
        {
            Downloader dld = new Downloader();
            Console.WriteLine(dld.Download("http://update.bazhuayu.com/clientsetup/%E5%85%AB%E7%88%AA%E9%B1%BC%E9%87%87%E9%9B%86%E5%99%A8V6.3%E7%89%88%E5%AE%89%E8%A3%85%E5%8C%85.zip", "D:/bazhuayu.zip"));
            //CacheSystem.InsertTestData();
            //CacheSystem.GetSQLiteData();
        }
    }
}
