package com.wm.app.sourcecode.demo.log;

import com.alibaba.fastjson.JSON;
import java.util.Map;
import java.util.Optional;

public interface IMessagePayload {
    String getType();

    String getDesc();

    String getScope();

    String name();

    default String getMsg() {
        return String.format("{\"scope\": \"%s\", \"type\": \"%s\", \"code\": \"%s\", \"desc\": \"%s\", \"data\": {}}", this.getScope(), this.getType(), this.name(), this.getDesc());
    }

    default String getMsg(Map<String, Object> data) {
        return Optional.ofNullable(data).isEmpty() ? this.getMsg() : String.format("{\"scope\": \"%s\", \"type\": \"%s\", \"code\": \"%s\", \"desc\": \"%s\", \"data\": %s}", this.getScope(), this.getType(), this.name(), this.getDesc(), JSON.toJSONString(data));
    }
}