package com.conan.mvcdemo.model.base;

import org.springframework.http.HttpStatus;

public class Restful<T> {

    private String errorInfo;
    private String errorCode;
    private HttpStatus state;
    private T data;

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public HttpStatus getState() {
        return state;
    }

    public void setState(HttpStatus state) {
        this.state = state;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
