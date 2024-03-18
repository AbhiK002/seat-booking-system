package com.project.sbs.api.requests;

import com.project.sbs.config.enums.SeatType;

public class SeatRequest {
    private Integer Floor_id;
    private String Seat_Number;
    private SeatType seatType;

    public SeatRequest() {
    }

    public SeatRequest(String seat_Number, SeatType seatType) {
        Seat_Number = seat_Number;
        this.seatType = seatType;
    }

    public Integer getFloor_id() {
        return Floor_id;
    }

    public void setFloor_id(Integer floor_id) {
        Floor_id = floor_id;
    }

    public String getSeat_Number() {
        return Seat_Number;
    }

    public void setSeat_Number(String seat_Number) {
        Seat_Number = seat_Number;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }
}
