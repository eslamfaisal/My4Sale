package com.fekrah.my4sale.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Category {

    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("categories")
    @Expose
    private List<CategoryData> categories;

    public Category() {
    }

    public int getCode() {
        return code;
    }

    public String getSuccess() {
        return success;
    }

    public List<CategoryData> getCategories() {
        return categories;
    }

    public class CategoryData {


        @SerializedName("id")
        @Expose
        private String  id;

        @SerializedName("name")
        @Expose
        private String name;

        public CategoryData() {
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}
