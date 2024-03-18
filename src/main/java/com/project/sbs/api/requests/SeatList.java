package com.project.sbs.api.requests;

import java.util.List;

public class SeatList {
    private List<SeatRequest> seats;

    public SeatList() {
    }

    public SeatList(List<SeatRequest> seats) {
        this.seats = seats;
    }

    public List<SeatRequest> getSeats() {
        return seats;
    }

    public void setSeats(List<SeatRequest> seats) {
        this.seats = seats;
    }
}
