package com.project.sbs.api.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.sbs.database.entities.Booking;

import java.util.List;

public class BookingResponse {
    @JsonProperty("data")
    private List<Booking> data;

    @JsonProperty("success")
    private boolean successField;

    public BookingResponse(List<Booking> data, boolean successField) {
        this.data = data;
        this.successField = successField;
    }

    public BookingResponse() {
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
