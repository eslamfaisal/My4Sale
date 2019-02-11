package com.fekrah.my4sale.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Cities {

    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("success")
    @Expose
    private boolean success;

    @SerializedName("cities")
    @Expose
    private List<CityData> cities;


    public Cities() {
    }

    public int getCode() {
        return code;
    }

    public boolean isSuccess() {
        return success;
    }

    public List<CityData> getCities() {
        return cities;
    }

    public class CityData {

        @SerializedName("id")
        @Expose
        private String id;

        @SerializedName("city_name")
        @Expose
        private String city_name;

        public CityData() {
        }

        public String getId() {
            return id;
        }

        public String getCity_name() {
            return city_name;
        }
    }


}
