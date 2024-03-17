package com.project.sbs.database.entities;

import jakarta.persistence.*;



@Entity
public class Cancellation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cancellation_id")
    private Long cancellationId;

    @Column(name = "cancellation_user_id")
    private User user;

    @Column(name = "cancellation_booking_id")
    private Integer booking;

    @Enumerated(EnumType.STRING)
    @Column(name = "cancellation_status")
    private String cancellationStatus;

    // Constructors, getters, and setters

    public Cancellation() {
    }

    public Cancellation(User user, Integer booking, String cancellationStatus) {
        this.user = user;
        this.booking = booking;
        this.cancellationStatus = cancellationStatus;
    }

    // Getters and Setters

    public Long getCancellationId() {
        return cancellationId;
    }

    public void setCancellationId(Long cancellationId) {
        this.cancellationId = cancellationId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getBooking() {
        return booking;
    }

    public void setBooking(Integer booking) {
        this.booking = booking;
    }

    public String getCancellationStatus() {
        return cancellationStatus;
    }

    public void setCancellationStatus(String cancellationStatus) {
        this.cancellationStatus = cancellationStatus;
    }
}

