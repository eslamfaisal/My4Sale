package com.fekrah.my4sale.models.fishes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FishesTypeData implements Serializable {

    @SerializedName("f_t_id")
    @Expose
    private String f_t_id;

    @SerializedName("f_name")
    @Expose
    private String f_name;

    public FishesTypeData() {
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

}
