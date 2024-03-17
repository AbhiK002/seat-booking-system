package com.project.sbs.api.requests;

public class RegisterRequest {
    private String user_fullname;
    private String user_email;
    private String user_password;
    private String user_otp;

    public RegisterRequest(String user_fullname, String user_email, String user_password, String user_otp) {
        this.user_fullname = user_fullname;
        this.user_email = user_email;
        this.user_password = user_password;
        this.user_otp = user_otp;
    }

    public String getUser_fullname() {
        return user_fullname;
    }

    public void setUser_fullname(String user_fullname) {
        this.user_fullname = user_fullname;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_otp() {
        return user_otp;
    }

    public void setUser_otp(String user_otp) {
        this.user_otp = user_otp;
    }
}
