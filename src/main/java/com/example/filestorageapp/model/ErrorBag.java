package com.example.filestorageapp.model;

public class ErrorBag {
    private String message;
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public ErrorBag setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ErrorBag setMessage(String message) {
        this.message = message;
        return this;
    }
}
