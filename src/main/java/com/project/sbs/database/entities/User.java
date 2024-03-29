package com.project.sbs.database.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "user_fullname")
    private String userFullname;

    @Column(name = "user_email", unique = true)
    private String userEmail;

    @Column(name = "user_password", length = 70)
    private String userPassword;

    @Column(name = "user_roles")
    private String userRoles;

    public User() {
    }

    public User(Integer userId, String userFullname, String userEmail, String userPassword, String userRoles) {
        this.userId = userId;
        this.userFullname = userFullname;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userRoles = userRoles;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserFullname() {
        return userFullname;
    }

    public void setUserFullname(String userFullname) {
        this.userFullname = userFullname;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(String userRoles) {
        this.userRoles = userRoles;
    }
}
