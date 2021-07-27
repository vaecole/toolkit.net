package com.qima.interview.measure;

import com.qima.interview.success.Succeeded;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Measure implements Succeeded {

    double result;
    double tolerance;
}
