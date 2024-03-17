package com.project.sbs.database.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "floors")
public class Floor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "floor_id")
    private Integer floorId;

    @Column(name="floor_number")
    private Integer floorNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "floor_office_id", referencedColumnName = "office_id")
    private Office floorOfficeId;

    public Floor() {
    }

    public Floor(Integer floorId, Integer floorNumber, Office floorOfficeId) {
        this.floorId = floorId;
        this.floorNumber = floorNumber;
        this.floorOfficeId = floorOfficeId;
    }

    public Integer getFloorId() {
        return floorId;
    }

    public void setFloorId(Integer floorId) {
        this.floorId = floorId;
    }

    public Integer getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(Integer floorNumber) {
        this.floorNumber = floorNumber;
    }

    public Office getFloorOfficeId() {
        return floorOfficeId;
    }

    public void setFloorOfficeId(Office floorOfficeId) {
        this.floorOfficeId = floorOfficeId;
    }
}
