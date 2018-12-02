package com.fekrah.my4sale.models.vehicle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Vehicle {

    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("success")
    @Expose
    private boolean success;

    @SerializedName("vehicles")
    @Expose
    private List< VehicleData >vehicles;

    public Vehicle() {
    }

    public int getCode() {
        return code;
    }

    public boolean isSuccess() {
        return success;
    }

    public List<VehicleData> getVehicles() {
        return vehicles;
    }
}
