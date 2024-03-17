package com.project.sbs.database.entities;

import jakarta.persistence.*;

@Table(name = "offices")
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "office_id")
    private Integer officeId;

    @Column(name = "office_name")
    private String officeName;
}
