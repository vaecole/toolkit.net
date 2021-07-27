package com.qima.interview.measure;

import com.qima.interview.success.Succeeded;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MeasureChecklist implements Succeeded {

    private String name;
    private MeasurementTolerance tolerance;
    private double toleranceValue;
    private List<Measure> measureList;
}
