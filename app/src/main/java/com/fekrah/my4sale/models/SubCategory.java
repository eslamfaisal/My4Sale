package com.fekrah.my4sale.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubCategory {

    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("sub_categories")
    @Expose
    private List<SubCategoryData> sub_categories;

    public SubCategory() {
    }

    public int getCode() {
        return code;
    }

    public String getSuccess() {
        return success;
    }

    public List<SubCategoryData> getSub_categories() {
        return sub_categories;
    }

    public class SubCategoryData{

        @SerializedName("sub_category_id")
        @Expose
        private String sub_category_id;

        @SerializedName("sub_category")
        @Expose
        private String sub_category;

        public SubCategoryData() {
        }

        public String  getSub_category_id() {
            return sub_category_id;
        }

        public String getSub_category() {
            return sub_category;
        }
    }
}
