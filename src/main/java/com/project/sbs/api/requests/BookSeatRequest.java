package com.project.sbs.api.requests;

public class BookSeatRequest {
    private Integer floor_id;
    private Integer seat_id;
    private String start_datetime;
    private String end_datetime;

    public BookSeatRequest() {}

    public BookSeatRequest(Integer floor_id, Integer seat_id, String start_datetime, String end_datetime) {
        this.floor_id = floor_id;
        this.seat_id = seat_id;
        this.start_datetime = start_datetime;
        this.end_datetime = end_datetime;
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

    public String getEnd_datetime() {
        return end_datetime;
    }

    public void setEnd_datetime(String end_datetime) {
        this.end_datetime = end_datetime;
    }
}
