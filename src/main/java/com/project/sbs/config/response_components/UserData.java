package com.project.sbs.config.response_components;

import com.project.sbs.database.entities.User;

public class UserData {
    private Integer user_id;
    private String user_fullname;
    private String user_email;
    private String user_roles;

    public UserData(Integer user_id, String user_fullname, String user_email, String user_roles) {
        this.user_id = user_id;
        this.user_fullname = user_fullname;
        this.user_email = user_email;
        this.user_roles = user_roles;
    }

    public UserData(User user) {
        this(user.getUserId(), user.getUserFullname(), user.getUserEmail(), user.getUserRoles());
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
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

    public String getUser_roles() {
        return user_roles;
    }

    public void setUser_roles(String user_roles) {
        this.user_roles = user_roles;
    }
}
