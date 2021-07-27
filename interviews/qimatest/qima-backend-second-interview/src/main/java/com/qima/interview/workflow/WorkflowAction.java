package com.qima.interview.workflow;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.qima.interview.success.Succeeded;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", include = JsonTypeInfo.As.EXISTING_PROPERTY, visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = WorkflowChecklistAction.class, name = "CHECKLIST_ACTION"),
        @JsonSubTypes.Type(value = WorkflowMeasurementAction.class, name = "MEASUREMENT_ACTION")
})
public abstract class WorkflowAction implements Succeeded {

    private String type;

}
