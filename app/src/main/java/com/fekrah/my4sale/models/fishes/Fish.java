package com.fekrah.my4sale.models.fishes;

import java.io.Serializable;

public class Fish implements Serializable {
    private String img;
    private String name;
    private String price;

    public Fish() {
    }

    public Fish(String img, String name, String price) {
        this.img = img;
        this.name = name;
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }
}
