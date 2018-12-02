package com.fekrah.my4sale.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Boat {

        @SerializedName("id")
        @Expose
        private String id;

        @SerializedName("name")
        @Expose
        private String name;


        public Boat() {
        }


        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }
}
