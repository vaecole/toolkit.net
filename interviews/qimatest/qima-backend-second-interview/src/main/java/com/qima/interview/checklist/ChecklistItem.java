package com.qima.interview.checklist;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.qima.interview.success.Succeeded;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, visible = true, property = "type", include = JsonTypeInfo.As.EXISTING_PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(name = "NUMBER", value = NumberItem.class),
        @JsonSubTypes.Type(name = "TEXT", value = TextItem.class),
        @JsonSubTypes.Type(name = "YES_NO", value = YesNoItem.class)
})
public abstract class ChecklistItem implements Succeeded {

    private String type;
    private boolean checkPoint;

    public void setCheckPoint(boolean checkPoint) {
        this.checkPoint = checkPoint;
    }
}
