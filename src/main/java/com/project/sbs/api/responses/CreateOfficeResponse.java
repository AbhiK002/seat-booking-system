package com.project.sbs.api.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.sbs.database.entities.Office;

public class CreateOfficeResponse {
    @JsonProperty
    Office office=new Office();

    @JsonProperty("success")
    private boolean successField;

    public CreateOfficeResponse() {
    }

    public CreateOfficeResponse(Office office, boolean successField) {
        this.office = office;
        this.successField = successField;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public boolean isSuccessField() {
        return successField;
    }

    public void setSuccessField(boolean successField) {
        this.successField = successField;
    }
}
