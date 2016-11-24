using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace common.Net
{
    public class APIVisitor
    {
        public void Visit(string token, string url, Dictionary<string, string> paras)
        {
            if (paras != null && paras.Count > 0)
            {
                url += "?";
                foreach (var para in paras)
                {
                    url += string.Format("{0}={1}&", para.Key, para.Value);
                }
            }
            Dictionary<string, string> headers = new Dictionary<string, string>(1);
            headers.Add("Authorization", string.Format("bearer {0}", token));
            string taskStr = HttpHelper.Get(url, headers);
            if (taskStr.Contains("\"data\":"))
            {
                JObject jsonTasks = JObject.Parse(taskStr);
                JArray jsonTasksJarray = jsonTasks["data"] as JArray;
                foreach (JToken jtokenTask in jsonTasksJarray)
                {

                }
            }
            else
            {

            }

        }

        public void Visit(string token, string url, string postdata)
        {
            Dictionary<string, string> headers = new Dictionary<string, string>(1);
            headers.Add("Authorization", string.Format("bearer {0}", token));
            string taskStr = HttpHelper.Post(url, postdata, headers);
            if (taskStr.Contains("\"data\":"))
            {
                JObject jsonTasks = JObject.Parse(taskStr);
                JArray jsonTasksJarray = jsonTasks["data"] as JArray;
                foreach (JToken jtokenTask in jsonTasksJarray)
                {

                }
            }
            else
            {

            }

        }


    }
}
