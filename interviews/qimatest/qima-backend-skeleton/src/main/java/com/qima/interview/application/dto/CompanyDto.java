package com.qima.interview.application.dto;

import javax.validation.constraints.NotEmpty;

public class CompanyDto {

    @NotEmpty
    private String name;
    private String address;
}
