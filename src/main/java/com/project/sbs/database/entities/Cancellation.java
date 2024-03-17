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
    private User cancellationUserId;

    @Column(name = "cancellation_booking_id")
    private Integer cancellationBookingId;

    @Enumerated(EnumType.STRING)
    @Column(name = "cancellation_status")
    private RequestStatus cancellationStatus;

    // Constructors, getters, and setters
    public Cancellation() {
    }

    public Cancellation(Integer cancellationId, User cancellationUserId, Integer cancellationBookingId, RequestStatus cancellationStatus) {
        this.cancellationId = cancellationId;
        this.cancellationUserId = cancellationUserId;
        this.cancellationBookingId = cancellationBookingId;
        this.cancellationStatus = cancellationStatus;
    }

    public Integer getCancellationId() {
        return cancellationId;
    }

    public void setCancellationId(Integer cancellationId) {
        this.cancellationId = cancellationId;
    }

    public User getCancellationUserId() {
        return cancellationUserId;
    }

    public void setCancellationUserId(User user) {
        this.cancellationUserId = user;
    }

    public Integer getCancellationBookingId() {
        return cancellationBookingId;
    }

    public void setCancellationBookingId(Integer booking) {
        this.cancellationBookingId = booking;
    }

    public RequestStatus getCancellationStatus() {
        return cancellationStatus;
    }

    public void setCancellationStatus(RequestStatus cancellationStatus) {
        this.cancellationStatus = cancellationStatus;
    }
}

