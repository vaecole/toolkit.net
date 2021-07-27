package com.qima.interview.application.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Company {

    @EqualsAndHashCode.Include
    private Long id;
    private String name;
    private String address;
    private List<Company> network;

    public Company id(long id) {
        this.id = id;
        return this;
    }

    public Company name(String name) {
        this.name = name;
        return this;
    }

    public Company address(String address) {
        this.address = address;
        return this;
    }
}
