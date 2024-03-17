package com.project.sbs.database.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "offices")
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "office_id")
    private Integer officeId;

    @Column(name = "office_name")
    private String officeName;

    public Office() {
    }

    public Office(String officeName) {
        this.officeName = officeName;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }
}
