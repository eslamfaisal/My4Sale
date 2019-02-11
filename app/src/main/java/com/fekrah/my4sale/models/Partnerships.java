package com.fekrah.my4sale.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Partnerships {

    @SerializedName("success")
    @Expose
    private boolean success;

    @SerializedName("partnerships")
    @Expose
    private List<PartnershipsData> partnerships;



    public Partnerships() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<PartnershipsData> getPartnerships() {
        return partnerships;
    }

    public void setPartnerships(List<PartnershipsData> partnerships) {
        this.partnerships = partnerships;
    }

    public class PartnershipsData {

        @SerializedName("id")
        @Expose
        private String id;

        @SerializedName("time")
        @Expose
        private String time;

        @SerializedName("price")
        @Expose
        private String price;

        public PartnershipsData() {
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }


}
