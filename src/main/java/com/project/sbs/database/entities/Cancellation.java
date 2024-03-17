package com.project.sbs.database.entities;

import com.project.sbs.config.enums.RequestStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "cancellations")
public class Cancellation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cancellation_id")
    private Integer cancellationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cancellation_user_id", referencedColumnName = "user_id")
    private User user;

    @Column(name = "cancellation_booking_id")
    private Integer booking;

    @Enumerated(EnumType.STRING)
    @Column(name = "cancellation_status")
    private RequestStatus cancellationStatus;

    // Constructors, getters, and setters
    public Cancellation() {
    }

    public Cancellation(Integer cancellationId, User user, Integer booking, RequestStatus cancellationStatus) {
        this.cancellationId = cancellationId;
        this.user = user;
        this.booking = booking;
        this.cancellationStatus = cancellationStatus;
    }

    public Integer getCancellationId() {
        return cancellationId;
    }

    public void setCancellationId(Integer cancellationId) {
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

    public RequestStatus getCancellationStatus() {
        return cancellationStatus;
    }

    public void setCancellationStatus(RequestStatus cancellationStatus) {
        this.cancellationStatus = cancellationStatus;
    }
}

