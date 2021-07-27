package com.qima.interview.checklist;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class YesNoItem extends ChecklistItem {

    boolean response;
    boolean expected;
}
