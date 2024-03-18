package com.project.sbs.api.requests;

public class ChangePasswordRequest {
    private String user_old_password;
    private String user_new_password;

    public ChangePasswordRequest(String user_old_password, String user_new_password) {
        this.user_old_password = user_old_password;
        this.user_new_password = user_new_password;
    }

    public String getUser_old_password() {
        return user_old_password;
    }

    public void setUser_old_password(String user_old_password) {
        this.user_old_password = user_old_password;
    }

    public String getUser_new_password() {
        return user_new_password;
    }

    public void setUser_new_password(String user_new_password) {
        this.user_new_password = user_new_password;
    }
}
