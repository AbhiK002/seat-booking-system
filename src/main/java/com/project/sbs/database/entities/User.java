package com.project.sbs.database.entities;

import jakarta.persistence.*;

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
}
