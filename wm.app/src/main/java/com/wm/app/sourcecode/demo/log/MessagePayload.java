package com.wm.app.sourcecode.demo.log;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class MessagePayload implements IMessagePayload {

    private Scope scope;
    private Layer layer;
    private ActionType action;
    private ActionResult result;

    @Override
    public String getType() {
        return String.format("%s_%s", layer.name(), result.name());
    }

    @Override
    public String getDesc() {
        return String.format("%s %s %s", result.getDesc(), action.getDesc(), getScope());
    }

    @Override
    public String getScope() {
        return this.scope.getDesc();
    }

    @Override
    public String name() {
        return String.format("%s_%s_%s", action.name(), scope.name(), result.name());
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public enum ActionType {
        GET("Get"),
        CREATE("Create"),
        UPDATE("Update"),
        DELETE("Delete");

        private String desc;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public enum ActionResult {
        OK("Successfully"),
        INFO("Info:"),
        EX("Unable to");

        private String desc;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public enum Scope {
        CERT_STATUS("certification status"),
        CERT_DRAFT("certification draft"),
        CERT_ORDER("certification order");

        private String desc;
    }

    public enum Layer {
        SERVICE,
        CONTROLLER,
        VALIDATION
    }

    public final class MsgFields {

        public static final String USER_ID = "userId";
        public static final String COMPANY_ID = "companyId";
        public static final String PARENT_ID = "parentId";
        public static final String DRAFT_ID = "draftId";
        public static final String DRAFT = "draft";
        public static final String ORDER_ID = "orderId";
    }
}

