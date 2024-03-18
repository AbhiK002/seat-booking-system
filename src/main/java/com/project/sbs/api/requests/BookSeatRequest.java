package com.project.sbs.api.requests;

public class BookSeatRequest {
    private Integer floor_id;
    private Integer seat_id;
    private String start_datetime;
    private Integer duration;

    public BookSeatRequest(Integer floor_id, Integer seat_id, String start_datetime, Integer duration) {
        this.floor_id = floor_id;
        this.seat_id = seat_id;
        this.start_datetime = start_datetime;
        this.duration = duration;
    }

    public Integer getFloor_id() {
        return floor_id;
    }

    public void setFloor_id(Integer floor_id) {
        this.floor_id = floor_id;
    }

    public Integer getSeat_id() {
        return seat_id;
    }

    public void setSeat_id(Integer seat_id) {
        this.seat_id = seat_id;
    }

    public String getStart_datetime() {
        return start_datetime;
    }

    public void setStart_datetime(String start_datetime) {
        this.start_datetime = start_datetime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
