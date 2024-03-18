package com.project.sbs.api.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.sbs.database.entities.Floor;

public class CreateFloorResponse extends SimpleResponse {
    @JsonProperty("Floor")
    private Floor floor;

    @JsonProperty("success")
    private boolean successField;

    public CreateFloorResponse() {
    }

    public CreateFloorResponse(Floor floor, boolean successField) {
        this.floor = floor;
        this.successField = successField;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public boolean isSuccessField() {
        return successField;
    }

    public void setSuccessField(boolean successField) {
        this.successField = successField;
    }
}
