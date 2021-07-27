package com.qima.interview.success;

public interface Succeeded {

    default boolean succeeded() {
        throw new RuntimeException("Should be implemented in the different classes");
    }
}
