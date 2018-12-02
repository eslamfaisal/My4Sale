package com.fekrah.my4sale.models.provider_orders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProviderTripOrderData {

    @SerializedName("order_id")
    @Expose
    private String order_id;
    @SerializedName("user_id")
    @Expose
    private String user_id;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("customer_id")
    @Expose
    private String customer_id;
    @SerializedName("customer_username")
    @Expose
    private String customer_username;
    @SerializedName("trip_id")
    @Expose
    private String trip_id;
    @SerializedName("trip_price")
    @Expose
    private String trip_price;
    @SerializedName("trip_image")
    @Expose
    private String trip_image;
    @SerializedName("number_of_tickets")
    @Expose
    private String number_of_tickets;
    @SerializedName("trip_time")
    @Expose
    private String trip_time;
    @SerializedName("trip_start_time")
    @Expose
    private String trip_start_time;
    @SerializedName("trip_end_time")
    @Expose
    private String trip_end_time;
    @SerializedName("trip_type")
    @Expose
    private String trip_type;
    @SerializedName("city_name")
    @Expose
    private String city_name;
    @SerializedName("trip_boatName")
    @Expose
    private String trip_boatName;
    @SerializedName("trip_date")
    @Expose
    private String trip_date;

    public ProviderTripOrderData() {
    }

    public String getOrder_id() {
        return order_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getUsername() {
        return username;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public String getCustomer_username() {
        return customer_username;
    }

    public String getTrip_id() {
        return trip_id;
    }

    public String getTrip_price() {
        return trip_price;
    }

    public String getTrip_image() {
        return trip_image;
    }

    public String getNumber_of_tickets() {
        return number_of_tickets;
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

    public String getTrip_type() {
        return trip_type;
    }

    public String getCity_name() {
        return city_name;
    }

    public String getTrip_boatName() {
        return trip_boatName;
    }

    public String getTrip_date() {
        return trip_date;
    }
}
