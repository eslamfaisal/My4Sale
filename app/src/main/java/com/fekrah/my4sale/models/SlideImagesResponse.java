package com.fekrah.my4sale.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SlideImagesResponse {

    @SerializedName("success")
    @Expose
    private boolean success;

    @SerializedName("slide_show")
    @Expose
    private List<ImageData> Images;

    public SlideImagesResponse() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<ImageData> getImages() {
        return Images;
    }

    public void setImages(List<ImageData> images) {
        Images = images;
    }

    public class ImageData{

        @SerializedName("image")
        @Expose
        private boolean image;

        public ImageData() {
        }

        public boolean isImage() {
            return image;
        }

        public void setImage(boolean image) {
            this.image = image;
        }
    }

}
