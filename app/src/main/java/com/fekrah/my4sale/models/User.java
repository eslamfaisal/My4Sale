package com.fekrah.my4sale.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {


    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("user")
    @Expose
    private UserData user;

    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("error")
    @Expose
    private List<Errors> error;

    public User() {
    }

    public String getSuccess() {
        return success;
    }

    public List<Errors> getError() {
        return error;
    }

    public String getCode() {
        return code;
    }

    public UserData getUser() {
        return user;
    }

    public class UserData{

        @SerializedName("user_id")
        @Expose
        private String user_id;

        @SerializedName("username")
        @Expose
        private String username;

        @SerializedName("first_name")
        @Expose
        private String first_name;

        @SerializedName("last_name")
        @Expose
        private String last_name;

        @SerializedName("email")
        @Expose
        private String email;

        @SerializedName("mobile")
        @Expose
        private String mobile;

        @SerializedName("city")
        @Expose
        private String city;

        @SerializedName("district")
        @Expose
        private String district;


        @SerializedName("image")
        @Expose
        private String image;

        @SerializedName("active")
        @Expose
        private String active;

        @SerializedName("token")
        @Expose
        private String token;


        public UserData() {
        }

        public String getUser_id() {
            return user_id;
        }

        public String getUsername() {
            return username;
        }

        public String getFirst_name() {
            return first_name;
        }

        public String getLast_name() {
            return last_name;
        }

        public String getEmail() {
            return email;
        }

        public String getMobile() {
            return mobile;
        }

        public String getCity() {
            return city;
        }

        public String getDistrict() {
            return district;
        }

        public String getImage() {
            return image;
        }

        public String getActive() {
            return active;
        }

        public String getToken() {
            return token;
        }
    }
}
