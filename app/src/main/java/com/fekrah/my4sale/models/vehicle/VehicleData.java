package com.fekrah.my4sale.models.vehicle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VehicleData {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("boat_name")
    @Expose
    private String boat_name;

    @SerializedName("passengers_number")
    @Expose
    private String passengers_number;

    public VehicleData() {
    }

    public String getId() {
        return id;
    }

    public String getBoat_name() {
        return boat_name;
    }

    public String getPassengers_number() {
        return passengers_number;
    }
}
