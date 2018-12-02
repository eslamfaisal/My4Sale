package com.fekrah.my4sale.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BuyResponse {

    @SerializedName("success")
    @Expose
    private boolean success;

    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("msg")
    @Expose
    private String msg;

    public BuyResponse() {
    }

    public boolean isSuccess() {
        return success;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
