package com.qima.interview.workflow;

import com.qima.interview.success.Succeeded;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WorkflowStep implements Succeeded {

    private List<WorkflowAction> actions;
}
