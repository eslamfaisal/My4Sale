package com.fekrah.my4sale.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Errors {

    @SerializedName("param")
    @Expose
    private String param;
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("msg")
    @Expose
    private String msg;

    public Errors() {
    }

    public String getParam() {
        return param;
    }

    public String getValue() {
        return value;
    }

    public String getMsg() {
        return msg;
    }
}