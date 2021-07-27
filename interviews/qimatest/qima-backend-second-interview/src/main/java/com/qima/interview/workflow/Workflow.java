package com.qima.interview.workflow;

import com.qima.interview.success.Succeeded;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Workflow implements Succeeded {
    private String name;
    private List<WorkflowStep> steps;
}
