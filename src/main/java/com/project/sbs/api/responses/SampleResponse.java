package com.project.sbs.api.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.sbs.database.entities.Booking;

import java.util.List;

public class SampleResponse {
    @JsonProperty("field-name")
    private List<Booking> data;

    @JsonProperty("success")
    private boolean successField;

    public SampleResponse(List<Booking> data, boolean successField) {
        this.data = data;
        this.successField = successField;
    }

    public SampleResponse() {
    }

    public List<Booking> getData() {
        return data;
    }

    public void setData(List<Booking> data) {
        this.data = data;
    }

    public boolean isSuccessField() {
        return successField;
    }

    public void setSuccessField(boolean successField) {
        this.successField = successField;
    }
}
