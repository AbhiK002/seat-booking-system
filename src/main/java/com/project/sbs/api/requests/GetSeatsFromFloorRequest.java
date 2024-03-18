package com.project.sbs.api.requests;

public class GetSeatsFromFloorRequest {
    private Integer floor_id;

    public GetSeatsFromFloorRequest(Integer floor_id) {
        this.floor_id = floor_id;
    }

    public Integer getFloor_id() {
        return floor_id;
    }

    public void setFloor_id(Integer floor_id) {
        this.floor_id = floor_id;
    }
}
