package com.wm.app.sourcecode.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import com.wm.app.sourcecode.demo.log.IMessagePayload;
import com.wm.app.sourcecode.demo.log.MessagePayload;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.Callable;

@Slf4j
public class LogBuilderDemo {
    public static IMessagePayload buildForController(MessagePayload.Scope scope, MessagePayload.ActionType action,
                                                     MessagePayload.ActionResult result) {
        return new MessagePayload(scope, MessagePayload.Layer.CONTROLLER, action, result);
    }

    public static ApiCallResult callServiceWithLogging(Callable<ApiCallResult> func,
                                                MessagePayload.Scope scope, MessagePayload.ActionType action, Map loggingFields) {
        log.info(buildForController(scope, action, MessagePayload.ActionResult.INFO).getMsg(loggingFields));
        try {
            return func.call();
        } catch (final Exception e) {
            final var msg = buildForController(scope, action, MessagePayload.ActionResult.EX)
                    .getMsg(loggingFields);
            log.error(msg, e);
            return new ApiCallResult<>(msg, null);
        }
    }

    public static class ApiCallResult<T> implements Serializable {
        private static final long serialVersionUID = 1996691926903571155L;
        private String message;
        private T content;

        public ApiCallResult() {
        }

        public ApiCallResult(String message, T content) {
            this.message = message;
            this.content = content;
        }

        public String getMessage() {
            return this.message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public T getContent() {
            return this.content;
        }

        public void setContent(T content) {
            this.content = content;
        }

        public String toString() {
            String var10000 = this.message;
            return "ApiCallResult [message=" + var10000 + ", content=" + JSON.toJSONString(this.content, new SerializerFeature[]{SerializerFeature.WriteMapNullValue}) + "]";
        }
    }

}
