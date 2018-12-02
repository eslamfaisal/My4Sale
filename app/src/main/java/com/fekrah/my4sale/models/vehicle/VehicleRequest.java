package com.fekrah.my4sale.models.vehicle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VehicleRequest {

    @SerializedName("AuthKey")
    @Expose
    private String AuthKey;

    @SerializedName("Content_Type")
    @Expose
    private String Content_Type;

    @SerializedName("c_id")
    @Expose
    private String c_id;

    @SerializedName("v_vehicle_type_id")
    @Expose
    private String v_vehicle_type_id;

    @SerializedName("boat_name")
    @Expose
    private String boat_name;


    @SerializedName("v_register_number")
    @Expose
    private String v_register_number;

    @SerializedName("v_des")
    @Expose
    private String v_des;

    @SerializedName("marasi_id")
    @Expose
    private String marasi_id;

    public VehicleRequest() {
    }

    public VehicleRequest(String authKey, String content_Type, String c_id, String v_vehicle_type_id, String boat_name, String v_register_number, String v_des, String marasi_id) {
        AuthKey = authKey;
        Content_Type = content_Type;
        this.c_id = c_id;
        this.v_vehicle_type_id = v_vehicle_type_id;
        this.boat_name = boat_name;
        this.v_register_number = v_register_number;
        this.v_des = v_des;
        this.marasi_id = marasi_id;
    }
}
