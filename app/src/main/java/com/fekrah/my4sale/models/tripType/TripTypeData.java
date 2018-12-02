package com.fekrah.my4sale.models.tripType;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TripTypeData {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("type")
    @Expose
    private String type;

    public TripTypeData() {
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}
