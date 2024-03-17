package com.project.sbs.database.entities;

import jakarta.persistence.*;

@Entity
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private Long seatId;

    @Column(name = "seat_number")
    private String seatNumber;

    @Column(name = "seat_type")
    private String seatType;

    @Column(name = "seat_floor_id")
    private Floor floor;

    @Column(name = "seat_booked")
    private boolean seatBooked;

    // Constructors, getters, and setters

    public Seat() {
    }

    public Seat(String seatNumber, String seatType, Floor floor, boolean seatBooked) {
        this.seatNumber = seatNumber;
        this.seatType = seatType;
        this.floor = floor;
        this.seatBooked = seatBooked;
    }

    // Getters and Setters

    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public boolean isSeatBooked() {
        return seatBooked;
    }

    public void setSeatBooked(boolean seatBooked) {
        this.seatBooked = seatBooked;
    }
}

