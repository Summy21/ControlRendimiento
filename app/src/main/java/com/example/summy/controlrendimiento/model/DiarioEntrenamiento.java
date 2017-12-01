package com.example.summy.controlrendimiento.model;

/**
 * Created by SUMMY on 12/1/2017.
 */

public class DiarioEntrenamiento {
    String frecDespertar;
    String volGral;


    public DiarioEntrenamiento(String frecDespertar, String volGral) {
        this.frecDespertar = frecDespertar;
        this.volGral = volGral;
    }

    public String getFrecDespertar() {
        return frecDespertar;
    }

    public String getVolGral() {
        return volGral;
    }
}
