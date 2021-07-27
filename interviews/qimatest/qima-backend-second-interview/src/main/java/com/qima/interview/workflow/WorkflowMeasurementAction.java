package com.qima.interview.workflow;

import com.qima.interview.measure.MeasureChecklist;
import com.qima.interview.success.Succeeded;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkflowMeasurementAction extends WorkflowAction implements Succeeded {

    private MeasureChecklist measureChecklist;
}
