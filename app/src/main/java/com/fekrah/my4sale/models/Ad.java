package com.fekrah.my4sale.models;

public class Ad {
    private String image;
    private String name;
    private String price;
    private String description;

    public Ad() {
    }

    public Ad(String image, String name, String price, String description) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
