package com.fekrah.my4sale.models.single_trip_ad;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SingleTripAd {

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("success")
    @Expose
    private boolean success;

    @SerializedName("last_page")
    @Expose
    private int last_page;

    @SerializedName("data")
    @Expose
    private SingleTripAdData data;

    @SerializedName("services")
    @Expose
    private List<String> services;
    public SingleTripAd() {
    }

    public List<String> getServices() {
        return services;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getLast_page() {
        return last_page;
    }

    public void setLast_page(int last_page) {
        this.last_page = last_page;
    }

    public SingleTripAdData getData() {
        return data;
    }

    public void setData(SingleTripAdData data) {
        this.data = data;
    }
}

