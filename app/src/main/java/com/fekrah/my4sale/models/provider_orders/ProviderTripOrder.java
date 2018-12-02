package com.fekrah.my4sale.models.provider_orders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProviderTripOrder {
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
    private List<ProviderTripOrderData> data;

    public ProviderTripOrder() {
    }

    public String getCode() {
        return code;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getLast_page() {
        return last_page;
    }

    public List<ProviderTripOrderData> getData() {
        return data;
    }
}
