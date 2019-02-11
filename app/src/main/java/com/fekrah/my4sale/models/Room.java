package com.fekrah.my4sale.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Room {

    @SerializedName("user_id")
    @Expose
    private String user_id;

    @SerializedName("chatWith")
    @Expose
    private String chatWith;

    @SerializedName("chatWithName")
    @Expose
    private String chatWithName;

    public Room() {
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getChatWith() {
        return chatWith;
    }

    public void setChatWith(String chatWith) {
        this.chatWith = chatWith;
    }

    public String getChatWithName() {
        return chatWithName;
    }

    public void setChatWithName(String chatWithName) {
        this.chatWithName = chatWithName;
    }
}
