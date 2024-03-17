package com.project.sbs.api.responses;

public class SuccessBooleanResponse extends SimpleResponse {
    private boolean success;

    public SuccessBooleanResponse(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
