package com.project.sbs.api.requests;

public class CancellationRequest {
    private Integer booking_id;

    public CancellationRequest(Integer booking_id) {
        this.booking_id = booking_id;
    }

    public Integer getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(Integer booking_id) {
        this.booking_id = booking_id;
    }
}
