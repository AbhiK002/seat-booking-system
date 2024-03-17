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

    @Column(name="floor_office_id")
    private Integer FloorOfficeId;

    public Floor() {
    }

    public Floor(Integer floorNumber, Integer floorOfficeId) {
        this.floorNumber = floorNumber;
        FloorOfficeId = floorOfficeId;
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

    public Integer getFloorOfficeId() {
        return FloorOfficeId;
    }

    public void setFloorOfficeId(Integer floorOfficeId) {
        FloorOfficeId = floorOfficeId;
    }
}
