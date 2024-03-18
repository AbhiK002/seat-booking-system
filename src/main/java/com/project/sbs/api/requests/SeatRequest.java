package com.project.sbs.api.requests;

import com.project.sbs.config.enums.SeatType;

public class SeatRequest {
    private Integer floor_id;
    private String seat_number;
    private SeatType seat_type;

    public SeatRequest() {
    }

    public SeatRequest(Integer floor_id, String seat_number, SeatType seat_type) {
        this.floor_id = floor_id;
        this.seat_number = seat_number;
        this.seat_type = seat_type;
    }

    public Integer getFloor_id() {
        return floor_id;
    }

    public void setFloor_id(Integer floor_id) {
        this.floor_id = floor_id;
    }

    public String getSeat_number() {
        return seat_number;
    }

    public void setSeat_number(String seat_number) {
        this.seat_number = seat_number;
    }

    public SeatType getSeat_type() {
        return seat_type;
    }

    public void setSeat_type(SeatType seat_type) {
        this.seat_type = seat_type;
    }
}
