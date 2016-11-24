using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
//using System.Workflow.Runtime.Configuration;

namespace common.Net
{
    class TaskHelper
    {
        public void startTask()
        {
            //int _duplicateCount = 0;
            //new Thread(delegate()
            //{
            //    try
            //    {

            //        if (this._runtime == null)
            //        {
            //            string xoml = "";// CompressHelper.UnZipText(this._currentTaskInfo.Xoml);
            //            using (XmlReader reader = XmlReader.Create(new StringReader(xoml)))
            //            {
            //                //initialize workflow runtime and service
            //                //this._runtime = new WorkflowRuntime();
            //                WorkflowRuntimeSection settings = new WorkflowRuntimeSection();
            //                settings.EnablePerformanceCounters = false;
            //                settings.ValidateOnCreate = false;
            //                this._runtime = new WorkflowRuntime(settings);
            //                this._exchangeService = new ExternalDataExchangeService();

            //                //add browser service
            //                IWorkFlowService browserService = new BrowserService(this);
            //                //IWorkFlowServiceHandler browserServiceHandler = new BrowserServiceHandler(browser);

            //                IWorkFlowServiceHandler browserServiceHandler = new BrowserServiceFireFoxHandler(this._browser);
            //                browserServiceHandler.RegisterHandler(browserService);
            //                //_services.Add(browserService, browserServiceHandler);

            //                //set up service
            //                this._runtime.AddService(_exchangeService);
            //                _exchangeService.AddService(browserService);

            //                XTrackingService trackingService = new XTrackingService();
            //                trackingService.OnTracking += new EventHandler<XTrackingEventArgs>(TrackingHandler);
            //                this._runtime.AddService(trackingService);

            //                // Subscribe to workflow events
            //                this._runtime.WorkflowCompleted += new EventHandler<WorkflowCompletedEventArgs>(_runtime_WorkflowCompleted);
            //                this._runtime.WorkflowStarted += new EventHandler<WorkflowEventArgs>(_runtime_WorkflowStarted);
            //                this._runtime.WorkflowTerminated += new EventHandler<WorkflowTerminatedEventArgs>(_runtime_WorkflowTerminated);

            //                // Start WorkflowRuntime
            //                this._runtime.StartRuntime();

            //                // Execute the SampleWorkflow Workflow
            //                this._workflow = this._runtime.CreateWorkflow(reader, null, null, new Guid(this._currentTaskInfo.TaskId));
            //                this._rootAction = this._workflow.GetWorkflowDefinition();
            //                SupplimentEnable = this._currentTaskInfo.SupplementEnable;
            //                if (SupplimentEnable)
            //                {
            //                    IncrementalExtractSettings increExtractSettings = GlobalCache.ServerService.GetIncrementalExtractSettings(_currentTaskInfo.TaskId);
            //                    if (increExtractSettings != null)
            //                    {
            //                        _concerParameter = increExtractSettings.CompareType != 0;
            //                        if (string.IsNullOrEmpty(increExtractSettings.SelectedParameters))
            //                        {
            //                            _parametersName = new string[0];
            //                        }
            //                        else
            //                        {
            //                            _parametersName = increExtractSettings.SelectedParameters.Split(new char[] { ',' }, StringSplitOptions.RemoveEmptyEntries);
            //                        }
            //                    }
            //                }
            //                this.Invoke(new Action(delegate()
            //                {
            //                    //initializeSchema                        
            //                    this.gridView_SampleData.Columns.Clear();
            //                    this.gridControl_SampleData.DataSource = GetExtractedDataContainer().MainTable;
            //                }));
            //                this._workflow.Start();
            //            }
            //        }
            //        else
            //        {
            //            RestartWorkflow(false);
            //        }
            //    }
            //    catch (Exception ex)
            //    {
            //        GlobalCache.ExceptionHandler.Process(ex);
            //        this.Invoke(new Action(delegate()
            //        {
            //            XtraMessageBox.Show("Test Task Failed, Error Message:\r\n" + ex.Message, "Test Task Failed");
            //            this.simpleButton_StopTest.Enabled = false;
            //            this.simpleButton_StartTest.Enabled = true;
            //            this.dropDownButton_Export.Enabled = true;
            //            this.simpleButton_UploadToServer.Enabled = !GlobalCache.IsOpenTip;
            //        }));
            //    }
            //}).Start();
        }
    }
}
