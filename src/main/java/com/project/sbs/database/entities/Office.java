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

    public Office(Integer officeId, String officeName) {
        this.officeId = officeId;
        this.officeName = officeName;
    }

    public Integer getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }
}
