package com.project.sbs.database.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "requests")
public class SwapRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Integer requestId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "request_booking_1_id", referencedColumnName = "booking_id")
    private Booking requestBooking1Id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "request_booking_2_id", referencedColumnName = "booking_id")
    private Booking requestBooking2Id;

    public SwapRequest() {}

    public SwapRequest(Integer requestId, Booking requestBooking1Id, Booking requestBooking2Id) {
        this.requestId = requestId;
        this.requestBooking1Id = requestBooking1Id;
        this.requestBooking2Id = requestBooking2Id;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public Booking getRequestBooking1Id() {
        return requestBooking1Id;
    }

    public void setRequestBooking1Id(Booking requestBooking1Id) {
        this.requestBooking1Id = requestBooking1Id;
    }

    public Booking getRequestBooking2Id() {
        return requestBooking2Id;
    }

    public void setRequestBooking2Id(Booking requestBooking2Id) {
        this.requestBooking2Id = requestBooking2Id;
    }
}
