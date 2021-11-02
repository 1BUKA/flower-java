package com.flower.demo.common.response;

public interface ResponseCode {

    String name();

    String getCode();

    String getMessage();

    int getArgs();

    default String description() {
        return this.name() + '-' + this.getCode() + '-' + this.getMessage() + '-' + this.getArgs();
    }
}
