package com.project.sbs.database.entities;

import com.project.sbs.config.enums.SeatType;
import jakarta.persistence.*;

@Entity
@Table(name = "seats")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private Long seatId;

    @Column(name = "seat_number")
    private String seatNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "seat_type")
    private SeatType seatType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_floor_id", referencedColumnName = "floor_id")
    private Floor floor;

    @Column(name = "seat_booked")
    private boolean seatBooked;

    // Constructors, getters, and setters

    public Seat() {
    }

    public Seat(Long seatId, String seatNumber, SeatType seatType, Floor floor, boolean seatBooked) {
        this.seatId = seatId;
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

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public boolean getSeatBooked() {
        return seatBooked;
    }

    public void setSeatBooked(boolean seatBooked) {
        this.seatBooked = seatBooked;
    }
}

