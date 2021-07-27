package com.qima.interview.workflow;

import com.qima.interview.checklist.TestChecklist;
import com.qima.interview.success.Succeeded;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkflowChecklistAction extends WorkflowAction implements Succeeded {

    private TestChecklist checklist;
}
