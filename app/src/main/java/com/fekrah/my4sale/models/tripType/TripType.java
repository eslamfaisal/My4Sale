package com.fekrah.my4sale.models.tripType;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class TripType implements Serializable {

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("types")
    @Expose
    private List<TripTypeData> types;


    public TripType() {
    }

    public String getCode() {
        return code;
    }

    public List<TripTypeData> getTypes() {
        return types;
    }
}
