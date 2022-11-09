
package com.example.mei_v.api.model.users;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UsersResponses {

    @SerializedName("data")
    private List<Users> mUsers;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("status")
    private String mStatus;

    public List<Users> getData() {
        return mUsers;
    }

    public void setData(List<Users> users) {
        mUsers = users;
    }

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
