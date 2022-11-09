package com.example.mei_v.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InputData {

    @SerializedName("message")
    private String mMessage;
    @SerializedName("status")
    private String mStatus;

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }
}
