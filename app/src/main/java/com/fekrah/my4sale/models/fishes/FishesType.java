package com.fekrah.my4sale.models.fishes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FishesType {

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("types")
    @Expose
    private List<FishesTypeData> types;

    public FishesType() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<FishesTypeData> getTypes() {
        return types;
    }

    public void setTypes(List<FishesTypeData> types) {
        this.types = types;
    }
}
