package com.sbs.common.entity;

import org.springframework.http.HttpStatus;

import java.util.HashMap;

public class SysResponse extends HashMap<String, Object> {

    private static final long serialVersionUID = -8713837118340960775L;

    public SysResponse code(HttpStatus status) {
        this.put("code", status.value());
        return this;
    }

    public SysResponse message(String message) {
        this.put("message", message);
        return this;
    }

    public SysResponse data(Object data) {
        this.put("data", data);
        return this;
    }

    public SysResponse success() {
        this.code(HttpStatus.OK);
        return this;
    }

    public SysResponse fail() {
        this.code(HttpStatus.INTERNAL_SERVER_ERROR);
        return this;
    }

    @Override
    public SysResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
