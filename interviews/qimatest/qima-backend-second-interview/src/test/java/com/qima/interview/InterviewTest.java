package com.qima.interview;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qima.interview.workflow.Workflow;
import com.qima.interview.workflow.WorkflowChecklistAction;
import com.qima.interview.workflow.WorkflowMeasurementAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

class InterviewTest {

    private Workflow workflow;

    @BeforeEach
    public void setUp() throws URISyntaxException, IOException {
        workflow = new ObjectMapper().readValue(Files.readAllBytes(Path.of(Objects.requireNonNull(this.getClass().getClassLoader().getResource("com/qima/interview/workflow.json")).toURI())), Workflow.class);
    }

    @Test
    void validTestChecklist() {
        assertThat(((WorkflowChecklistAction) workflow.getSteps().get(0).getActions().get(0)).getChecklist().getChecklistItemList().get(0).succeeded()).isTrue();
        assertThat(((WorkflowChecklistAction) workflow.getSteps().get(0).getActions().get(0)).getChecklist().getChecklistItemList().get(1).succeeded()).isFalse();
        assertThat(((WorkflowChecklistAction) workflow.getSteps().get(0).getActions().get(0)).getChecklist().getChecklistItemList().get(2).succeeded()).isFalse();
        assertThat(((WorkflowChecklistAction) workflow.getSteps().get(0).getActions().get(0)).getChecklist().succeeded()).isFalse();
    }

    @Test
    void validMeasurement() {
        assertThat(((WorkflowMeasurementAction) workflow.getSteps().get(1).getActions().get(0)).getMeasureChecklist().getMeasureList().get(0).succeeded()).isTrue();
        assertThat(((WorkflowMeasurementAction) workflow.getSteps().get(1).getActions().get(0)).getMeasureChecklist().getMeasureList().get(1).succeeded()).isTrue();
        assertThat(((WorkflowMeasurementAction) workflow.getSteps().get(1).getActions().get(0)).getMeasureChecklist().succeeded()).isTrue();

        assertThat(((WorkflowMeasurementAction) workflow.getSteps().get(1).getActions().get(1)).getMeasureChecklist().getMeasureList().get(0).succeeded()).isFalse();
        assertThat(((WorkflowMeasurementAction) workflow.getSteps().get(1).getActions().get(1)).getMeasureChecklist().getMeasureList().get(1).succeeded()).isTrue();
        assertThat(((WorkflowMeasurementAction) workflow.getSteps().get(1).getActions().get(1)).getMeasureChecklist().succeeded()).isTrue();
    }

    @Test
    void validStepsResults() {
        assertThat(workflow.getSteps().get(0).succeeded()).isTrue();
        assertThat(workflow.getSteps().get(1).succeeded()).isFalse();
    }

    @Test
    void validGlobalResults() {
        assertThat(workflow.succeeded()).isFalse();
    }
}
