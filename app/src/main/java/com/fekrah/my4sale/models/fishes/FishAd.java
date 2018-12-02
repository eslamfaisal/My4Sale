package com.fekrah.my4sale.models.fishes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FishAd {

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
    private List<FishAdData> data;

    public FishAd() {
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

    public List<FishAdData> getData() {
        return data;
    }

    public void setData(List<FishAdData> data) {
        this.data = data;
    }
}

