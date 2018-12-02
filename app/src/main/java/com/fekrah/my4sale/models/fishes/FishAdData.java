package com.fekrah.my4sale.models.fishes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FishAdData {

    @SerializedName("f_id")
    @Expose
    private String  f_id;

    @SerializedName("customer_id")
    @Expose
    private String  customer_id;

    @SerializedName("f_type_id")
    @Expose
    private String  f_type_id;

    @SerializedName("f_weight")
    @Expose
    private String  f_weight;

    @SerializedName("f_price")
    @Expose
    private String  f_price;

    @SerializedName("f_quantity")
    @Expose
    private String  f_quantity;

    @SerializedName("f_image")
    @Expose
    private String  f_image;

    @SerializedName("f_delevary")
    @Expose
    private String  f_delevary;

    @SerializedName("f_cooking")
    @Expose
    private String  f_cooking;

    @SerializedName("f_des")
    @Expose
    private String  f_des;

    @SerializedName("city_id")
    @Expose
    private String  city_id;

    @SerializedName("f_t_id")
    @Expose
    private String  f_t_id;

    @SerializedName("f_name")
    @Expose
    private String  f_name;

    @SerializedName("c_id")
    @Expose
    private String  c_id;

    @SerializedName("c_username")
    @Expose
    private String  c_username;

    public FishAdData() {
    }

    public String getF_id() {
        return f_id;
    }

    public void setF_id(String f_id) {
        this.f_id = f_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getF_type_id() {
        return f_type_id;
    }

    public void setF_type_id(String f_type_id) {
        this.f_type_id = f_type_id;
    }

    public String getF_weight() {
        return f_weight;
    }

    public void setF_weight(String f_weight) {
        this.f_weight = f_weight;
    }

    public String getF_price() {
        return f_price;
    }

    public void setF_price(String f_price) {
        this.f_price = f_price;
    }

    public String getF_quantity() {
        return f_quantity;
    }

    public void setF_quantity(String f_quantity) {
        this.f_quantity = f_quantity;
    }

    public String getF_image() {
        return f_image;
    }

    public void setF_image(String f_image) {
        this.f_image = f_image;
    }

    public String getF_delevary() {
        return f_delevary;
    }

    public void setF_delevary(String f_delevary) {
        this.f_delevary = f_delevary;
    }

    public String getF_cooking() {
        return f_cooking;
    }

    public void setF_cooking(String f_cooking) {
        this.f_cooking = f_cooking;
    }

    public String getF_des() {
        return f_des;
    }

    public void setF_des(String f_des) {
        this.f_des = f_des;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getF_t_id() {
        return f_t_id;
    }

    public void setF_t_id(String f_t_id) {
        this.f_t_id = f_t_id;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }

    public String getC_username() {
        return c_username;
    }

    public void setC_username(String c_username) {
        this.c_username = c_username;
    }
}
