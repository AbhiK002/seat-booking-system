package com.project.sbs.api.requests;

public class ModifyCancellation {
    private Integer cancellation_id;
    private boolean accepted;

    public ModifyCancellation() {

    }

    public ModifyCancellation(Integer cancellation_id, boolean accepted) {
        this.cancellation_id = cancellation_id;
        this.accepted = accepted;
    }

    public Integer getCancellation_id() {
        return cancellation_id;
    }

    public void setCancellation_id(Integer cancellation_id) {
        this.cancellation_id = cancellation_id;
    }

    public boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
