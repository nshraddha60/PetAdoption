package com.exp.android_user.canteenautomation;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 1/8/2018.
 */

public class InsertResponseModel {
    @SerializedName("success")
    private int status;
    @SerializedName("message")
    private String message;

    public InsertResponseModel(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public InsertResponseModel() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
