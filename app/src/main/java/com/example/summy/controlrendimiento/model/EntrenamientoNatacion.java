package com.example.summy.controlrendimiento.model;

/**
 * Created by SUMMY on 11/27/2017.
 */

public class EntrenamientoNatacion {

    String key;
    String calentamiento;
    String fasePrinc1;
    String fasePrinc2;
    String faseFund;
    String vueltaCalma;

    public EntrenamientoNatacion() {
    }

    public EntrenamientoNatacion(String key, String calentamiento, String fasePrinc1N, String fasePrinc2N, String faseFundN, String vueltaCalma) {
        this.key = key;
        this.calentamiento = calentamiento;
        this.fasePrinc1 = fasePrinc1N;
        this.fasePrinc2 = fasePrinc2N;
        this.faseFund = faseFundN;
        this.vueltaCalma = vueltaCalma;
    }

    public String getCalentamiento() {
        return calentamiento;
    }

    public String getFasePrinc1() {
        return fasePrinc1;
    }

    public String getFasePrinc2() {
        return fasePrinc2;
    }

    public String getFaseFund() {
        return faseFund;
    }

    public String getKey() {
        return key;
    }

    public String getVueltaCalma() {
        return vueltaCalma;
    }
}
