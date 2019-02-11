package com.fekrah.my4sale.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Ad implements Serializable{
    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("success")
    @Expose
    private boolean success;

    @SerializedName("last_page")
    @Expose
    private int last_page;

    @SerializedName("advertisements")
    @Expose
    private List<AdData> advertisements;

    @SerializedName("ads")
    @Expose
    private List<AdData> ads;


    public Ad() {
    }

    public List<AdData> getAds() {
        return ads;
    }

    public int getCode() {
        return code;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getLast_page() {
        return last_page;
    }

    public List<AdData> getAdvertisements() {
        return advertisements;
    }

    public static class AdData  implements Serializable {

        @SerializedName("id")
        @Expose
        private String id;

        @SerializedName("cat_id")
        @Expose
        private String cat_id;

        @SerializedName("sub_category_id")
        @Expose
        private String sub_category_id;

        @SerializedName("sub_category_title")
        @Expose
        private String sub_category_title;

        @SerializedName("category_title")
        @Expose
        private String category_title;

        @SerializedName("type")
        @Expose
        private String type;

        @SerializedName("title")
        @Expose
        private String title;


        @SerializedName("price")
        @Expose
        private String price;

        @SerializedName("city")
        @Expose
        private String city;

        @SerializedName("mobile")
        @Expose
        private String mobile;

        @SerializedName("whatsapp")
        @Expose
        private String whatsapp;

        @SerializedName("district")
        @Expose
        private String district;

        @SerializedName("image")
        @Expose
        private String image;

        @SerializedName("user_id")
        @Expose
        private String user_id;

        @SerializedName("username")
        @Expose
        private String username;

        @SerializedName("email")
        @Expose
        private String email;

        @SerializedName("user_image")
        @Expose
        private String user_image;

        @SerializedName("des")
        @Expose
        private String des;

        public AdData() {
        }

        public String getId() {
            return id;
        }

        public String getCat_id() {
            return cat_id;
        }

        public String getSub_category_id() {
            return sub_category_id;
        }

        public String getSub_category_title() {
            return sub_category_title;
        }

        public String getCategory_title() {
            return category_title;
        }

        public String getType() {
            return type;
        }

        public String getTitle() {
            return title;
        }

        public String getPrice() {
            return price;
        }

        public String getCity() {
            return city;
        }

        public String getMobile() {
            return mobile;
        }

        public String getWhatsapp() {
            return whatsapp;
        }

        public String getDistrict() {
            return district;
        }

        public String getImage() {
            return image;
        }

        public String getUser_id() {
            return user_id;
        }

        public String getUsername() {
            return username;
        }

        public String getEmail() {
            return email;
        }

        public String getUser_image() {
            return user_image;
        }

        public String getDes() {
            return des;
        }
    }
}
