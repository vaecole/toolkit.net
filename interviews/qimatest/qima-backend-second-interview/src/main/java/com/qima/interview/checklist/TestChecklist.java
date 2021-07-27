package com.qima.interview.checklist;

import com.qima.interview.success.Succeeded;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TestChecklist implements Succeeded {

    private String name;
    private List<ChecklistItem> checklistItemList;
}
