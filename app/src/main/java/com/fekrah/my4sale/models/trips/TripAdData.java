package com.fekrah.my4sale.models.trips;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TripAdData implements Serializable {

    @SerializedName("trip_id")
    @Expose
    private String trip_id;

    @SerializedName("vehicle_type")
    @Expose
    private String vehicle_type;

    @SerializedName("customer_id")
    @Expose
    private String customer_id;

    @SerializedName("trip_vehicle_id")
    @Expose
    private String trip_vehicle_id;

    @SerializedName("trip_beach_name")
    @Expose
    private String trip_beach_name;

    @SerializedName("marasi_name")
    @Expose
    private String marasi_name;

    @SerializedName("city_name")
    @Expose
    private String city_name;

    @SerializedName("trip_type")
    @Expose
    private String trip_type;

    @SerializedName("trip_boatName")
    @Expose
    private String trip_boatName;

    @SerializedName("trip_time")
    @Expose
    private String trip_time;

    @SerializedName("trip_start_time")
    @Expose
    private String trip_start_time;

    @SerializedName("trip_end_time")
    @Expose
    private String trip_end_time;

    @SerializedName("trip_price")
    @Expose
    private String trip_price;

    @SerializedName("customer_name")
    @Expose
    private String customer_name;

    @SerializedName("trip_image")
    @Expose
    private String trip_image;

    @SerializedName("passengers")
    @Expose
    private String passengers;

    @SerializedName("trip_date")
    @Expose
    private String trip_date;

    public TripAdData() {
    }

    public String getTrip_date() {
        return trip_date;
    }

    public String getPassengers() {
        return passengers;
    }

    public String getTrip_id() {
        return trip_id;
    }

    public String getVehicle_type() {
        return vehicle_type;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public String getTrip_vehicle_id() {
        return trip_vehicle_id;
    }

    public String getTrip_beach_name() {
        return trip_beach_name;
    }

    public String getMarasi_name() {
        return marasi_name;
    }

    public String getCity_name() {
        return city_name;
    }

    public String getTrip_type() {
        return trip_type;
    }

    public String getTrip_boatName() {
        return trip_boatName;
    }

    public String getTrip_time() {
        return trip_time;
    }

    public String getTrip_start_time() {
        return trip_start_time;
    }

    public String getTrip_end_time() {
        return trip_end_time;
    }

    public String getTrip_price() {
        return trip_price;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public String getTrip_image() {
        return trip_image;
    }
}
