package com.project.sbs.api.responses;

import com.project.sbs.config.response_components.UserData;

public class LoginSuccessfulResponse extends SimpleResponse {
    private UserData data;
    private String token;
    private boolean success;

    public LoginSuccessfulResponse(UserData data, String token, boolean success) {
        this.data = data;
        this.token = token;
        this.success = success;
    }

    public UserData getData() {
        return data;
    }

    public void setData(UserData data) {
        this.data = data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
