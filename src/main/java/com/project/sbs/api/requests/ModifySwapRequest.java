package com.project.sbs.api.requests;


public class ModifySwapRequest {
    private Integer request_id;
    private boolean accepted;

    public ModifySwapRequest() {
    }

    public ModifySwapRequest(Integer request_id, boolean accepted) {
        this.request_id = request_id;
        this.accepted = accepted;
    }

    public Integer getRequest_id() {
        return request_id;
    }

    public void setRequest_id(Integer request_id) {
        this.request_id = request_id;
    }

    public boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
