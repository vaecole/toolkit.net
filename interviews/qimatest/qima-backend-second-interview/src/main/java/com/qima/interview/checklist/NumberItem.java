package com.qima.interview.checklist;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NumberItem extends ChecklistItem {

    private double response;
    private double expected;
    private NumberComparator numberComparator;

    @Override
    public boolean succeeded() {
        if (numberComparator == null) {
            throw new RuntimeException("Invalid numberComparator");
        }
        switch (numberComparator) {
            case EQUAL:
                return response == expected;
            case GREATER:
                return response > expected;
            case SMALLER:
                return response < expected;
        }
        throw new RuntimeException("Not supported!");
    }
}
