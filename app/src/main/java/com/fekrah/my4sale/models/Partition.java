package com.fekrah.my4sale.models;

public class Partition {

    private String id;

    private String name;

    public Partition() {
    }

    public Partition(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
