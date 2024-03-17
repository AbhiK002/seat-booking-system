package com.project.sbs.database.entities;

import com.project.sbs.config.enums.RequestStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Integer bookingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_user_id", referencedColumnName = "user_id")
    private User bookingUserId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_floor_id", referencedColumnName = "floor_id")
    private Floor bookingFloorId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_seat_id", referencedColumnName = "seat_id")
    private Seat bookingSeatId;

    @Column(name = "booking_start_datetime")
    private String bookingStartDatetime;

    @Column(name = "booking_end_datetime")
    private String bookingEndDatetime;

    @Enumerated(EnumType.STRING)
    @Column(name = "booking_status")
    private RequestStatus bookingStatus;

    public Booking() {}

    public Booking(Integer bookingId, User bookingUserId, Floor bookingFloorId, Seat bookingSeatId, String bookingStartDatetime, String bookingEndDatetime, RequestStatus bookingStatus) {
        this.bookingId = bookingId;
        this.bookingUserId = bookingUserId;
        this.bookingFloorId = bookingFloorId;
        this.bookingSeatId = bookingSeatId;
        this.bookingStartDatetime = bookingStartDatetime;
        this.bookingEndDatetime = bookingEndDatetime;
        this.bookingStatus = bookingStatus;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public User getBookingUserId() {
        return bookingUserId;
    }

    public void setBookingUserId(User bookingUserId) {
        this.bookingUserId = bookingUserId;
    }

    public Floor getBookingFloorId() {
        return bookingFloorId;
    }

    public void setBookingFloorId(Floor bookingFloorId) {
        this.bookingFloorId = bookingFloorId;
    }

    public Seat getBookingSeatId() {
        return bookingSeatId;
    }

    public void setBookingSeatId(Seat bookingSeatId) {
        this.bookingSeatId = bookingSeatId;
    }

    public String getBookingStartDatetime() {
        return bookingStartDatetime;
    }

    public void setBookingStartDatetime(String bookingStartDatetime) {
        this.bookingStartDatetime = bookingStartDatetime;
    }

    public String getBookingEndDatetime() {
        return bookingEndDatetime;
    }

    public void setBookingEndDatetime(String bookingEndDatetime) {
        this.bookingEndDatetime = bookingEndDatetime;
    }

    public RequestStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(RequestStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}
