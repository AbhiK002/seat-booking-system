package com.project.sbs.api.responses;

import java.util.List;

public class AnyListResponse<T> extends SimpleResponse {
    private List<T> data;
    private boolean success;

    public AnyListResponse(List<T> data, boolean success) {
        this.data = data;
        this.success = success;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
