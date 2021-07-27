package com.qima.interview.application.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product {

    @EqualsAndHashCode.Include
    private Long id;
    private String name;
    private String description;
    private String gtin13;
    private Company company;

    public Product id(long id) {
        this.id = id;
        return this;
    }

    public Product name(String name) {
        this.name = name;
        return this;
    }

    public Product company(Company company) {
        this.company = company;
        return this;
    }

    public Product gtin13(String gtin13) {
        this.gtin13 = gtin13;
        return this;
    }
}
