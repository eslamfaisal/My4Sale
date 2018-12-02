package com.fekrah.my4sale.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Marsa {

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("marasi")
    @Expose
    private List<MarsaData> marasi;


    public Marsa() {
    }

    public String getCode() {
        return code;
    }

    public String getSuccess() {
        return success;
    }

    public List<MarsaData> getMarasi() {
        return marasi;
    }

    public class MarsaData {

        @SerializedName("m_id")
        @Expose
        private String m_id;

        @SerializedName("m_name")
        @Expose
        private String _name;

        public MarsaData() {
        }

        public String getM_id() {
            return m_id;
        }

        public String get_name() {
            return _name;
        }
    }
}
