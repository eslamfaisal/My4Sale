package com.fekrah.my4sale.models.provider_orders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProviderFishOrderData {

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

    @SerializedName("fish_id")
    @Expose
    private String fish_id;

    @SerializedName("fish_price")
    @Expose
    private String fish_price;

    @SerializedName("fish_image")
    @Expose
    private String fish_image;

    @SerializedName("fish_weight")
    @Expose
    private String fish_weight;

    @SerializedName("fish_delevary")
    @Expose
    private String fish_delevary;

    @SerializedName("fish_cooking")
    @Expose
    private String fish_cooking;

    @SerializedName("type")
    @Expose
    private String type;

    public ProviderFishOrderData() {
    }

    public String getType() {
        return type;
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

    public String getFish_id() {
        return fish_id;
    }

    public String getFish_price() {
        return fish_price;
    }

    public String getFish_image() {
        return fish_image;
    }

    public String getFish_weight() {
        return fish_weight;
    }

    public String getFish_delevary() {
        return fish_delevary;
    }

    public String getFish_cooking() {
        return fish_cooking;
    }
}
