package com.project.sbs.api.responses;

public class ErrorResponse extends SimpleResponse {
    private String message;
    private boolean success;

    public ErrorResponse(String message) {
        this.message = message;
        this.success = false;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
