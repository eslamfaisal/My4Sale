package com.fekrah.my4sale.models.periods;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PeriodDate {
    @SerializedName("t_id")
    @Expose
    private String t_id;

    @SerializedName("t_time")
    @Expose
    private String t_time;

    public PeriodDate() {
    }

    public String getT_id() {
        return t_id;
    }

    public String getT_time() {
        return t_time;
    }
}
