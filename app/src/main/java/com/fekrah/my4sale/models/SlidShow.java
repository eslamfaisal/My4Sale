package com.fekrah.my4sale.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SlidShow {

    @SerializedName("succes")
    @Expose
    private boolean success;

    @SerializedName("slide_show")
    @Expose
    private List<SlidImages> slide_show;

    public SlidShow() {
    }

    public boolean isSuccess() {
        return success;
    }

    public List<SlidImages> getSlide_show() {
        return slide_show;
    }

    public class SlidImages {

        @SerializedName("image")
        @Expose
        private String  image;

        public SlidImages() {
        }

        public String getImage() {
            return image;
        }
    }

}
