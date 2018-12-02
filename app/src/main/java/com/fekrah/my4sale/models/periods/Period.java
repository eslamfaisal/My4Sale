package com.fekrah.my4sale.models.periods;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Period {

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("periods")
    @Expose
    private List<PeriodDate> periods;

    public Period() {

    }

    public List<PeriodDate> getPeriods() {
        return periods;
    }

    public String getCode() {
        return code;
    }
}
