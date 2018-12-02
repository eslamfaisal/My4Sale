package com.fekrah.my4sale.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {


    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("user")
    @Expose
    private UserData user;

    public User() {
    }

    public String getCode() {
        return code;
    }

    public UserData getUser() {
        return user;
    }

    public class UserData{
        @SerializedName("name")
        @Expose
        private String name;

        @SerializedName("email")
        @Expose
        private String email;

        @SerializedName("mobile")
        @Expose
        private String mobile;

        @SerializedName("address")
        @Expose
        private String address;

        @SerializedName("token")
        @Expose
        private String token;

        @SerializedName("image")
        @Expose
        private String image;

        @SerializedName("customer_id")
        @Expose
        private String customer_id;

        @SerializedName("user_id")
        @Expose
        private String user_id;

        public UserData() {
        }

        public String getUser_id() {
            return user_id;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getMobile() {
            return mobile;
        }

        public String getAddress() {
            return address;
        }

        public String getToken() {
            return token;
        }

        public String getImage() {
            return image;
        }

        public String getCustomer_id() {
            return customer_id;
        }
    }
}
