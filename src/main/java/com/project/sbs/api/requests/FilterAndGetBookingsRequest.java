package com.project.sbs.api.requests;

public class FilterAndGetBookingsRequest {
    private Integer floor_id;
    private String start_time;
    private Integer duration;

    public FilterAndGetBookingsRequest(Integer floor_id, String start_time, Integer duration) {
        this.floor_id = floor_id;
        this.start_time = start_time;
        this.duration = duration;
    }

    public Integer getFloor_id() {
        return floor_id;
    }

    public void setFloor_id(Integer floor_id) {
        this.floor_id = floor_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
