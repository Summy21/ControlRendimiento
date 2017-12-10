package com.example.summy.controlrendimiento.model;

/**
 * Created by SUMMY on 12/5/2017.
 */

public class EntrenamientoRut {

    String aer;
    String ael;
    String aem;
    String aei;
    String pae;
    String cla;
    String pla;
    String cala;
    String pala;
    String volumenT;
    String volumen;

    public EntrenamientoRut() {
    }

    public EntrenamientoRut(String aer,
                            String ael,
                            String aem,
                            String aei,
                            String pae,
                            String cla,
                            String pla,
                            String cala,
                            String pala,
                            String volumenT,
                            String volumen) {
        this.aer = aer;
        this.ael = ael;
        this.aem = aem;
        this.aei = aei;
        this.pae = pae;
        this.cla = cla;
        this.pla = pla;
        this.cala = cala;
        this.pala = pala;
        this.volumenT = volumenT;
        this.volumen = volumen;
    }

    public String getAer() {
        return aer;
    }

    public String getAel() {
        return ael;
    }

    public String getAem() {
        return aem;
    }

    public String getAei() {
        return aei;
    }

    public String getPae() {
        return pae;
    }

    public String getCla() {
        return cla;
    }

    public String getPla() {
        return pla;
    }

    public String getCala() {
        return cala;
    }

    public String getPala() {
        return pala;
    }

    public String getVolumen() {
        return volumen;
    }

    public String getVolumenT() {
        return volumenT;
    }
}