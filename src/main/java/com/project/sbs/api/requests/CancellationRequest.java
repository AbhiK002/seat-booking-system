package com.project.sbs.api.requests;

public class CancellationRequest {
    private String booking_id;

    public CancellationRequest(String booking_id) {
        this.booking_id = booking_id;
    }

    public String getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(String booking_id) {
        this.booking_id = booking_id;
    }
}
