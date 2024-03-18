package com.project.sbs.api.requests;

public class ModifyBooking {
    private Integer booking_id;
    private boolean accepted;

    public ModifyBooking(Integer booking_id, boolean accepted) {
        this.booking_id = booking_id;
        this.accepted = accepted;
    }

    public ModifyBooking() {
    }

    public Integer getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(Integer booking_id) {
        this.booking_id = booking_id;
    }

    public boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
