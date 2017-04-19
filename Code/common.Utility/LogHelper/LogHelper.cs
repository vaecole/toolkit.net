using System;
using System.IO;

namespace common.Utility.LogHelper
{
    class LogHelper
    {
        public static string AppStartPath = Path.GetDirectoryName(System.Reflection.Assembly.GetExecutingAssembly().Location);

        public static void Log(string logInfo)
        {
            using (StreamWriter writer = File.AppendText(AppStartPath + "\\myLog.log"))
            {
                writer.WriteLine(DateTime.Now.ToString() + " " + logInfo);
            }
        }

        public static void Log(Exception ex)
        {
            using (StreamWriter writer = File.AppendText(AppStartPath + "\\myLog.log"))
            {
                writer.WriteLine("==================================================");
                writer.WriteLine(DateTime.Now.ToString() + " " + ex.Message);
                writer.WriteLine(DateTime.Now.ToString() + " " + ex.InnerException);
                writer.WriteLine(DateTime.Now.ToString() + " " + ex.StackTrace);
                writer.WriteLine("==================================================");
            }
        }
    }
}
