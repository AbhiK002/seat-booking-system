package com.project.sbs.api.requests;

public class EditUserDetailsRequest {
    private String user_fullname;

    public EditUserDetailsRequest(String user_fullname) {
        this.user_fullname = user_fullname;
    }

    public String getUser_fullname() {
        return user_fullname;
    }

    public void setUser_fullname(String user_fullname) {
        this.user_fullname = user_fullname;
    }
}
