package com.project.sbs.api.responses;

public class AnyObjectResponse<T> extends SimpleResponse {
    private T data;
    private boolean success;

    public AnyObjectResponse(T data, boolean success) {
        this.data = data;
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
