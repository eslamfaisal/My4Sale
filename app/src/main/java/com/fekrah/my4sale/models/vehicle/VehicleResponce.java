package com.fekrah.my4sale.models.vehicle;

import com.fekrah.my4sale.models.Errors;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VehicleResponce {

    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("success")
    @Expose
    private boolean success;

    @SerializedName("data")
    @Expose
    private String data;

    @SerializedName("error")
    @Expose
    private List<Errors> error;


    public VehicleResponce() {
    }


    public List<Errors> getError() {
        return error;
    }

    public int getCode() {
        return code;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getData() {
        return data;
    }


}
