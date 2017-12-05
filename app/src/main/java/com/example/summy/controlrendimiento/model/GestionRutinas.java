package com.example.summy.controlrendimiento.model;

/**
 * Created by SUMMY on 12/5/2017.
 */

public class GestionRutinas {

    String microciclo;
    String mesosiclo;
    String etapa;
    String periodo;
    String nroSesiones;
    String aer;
    String ael;
    String aem;
    String aei;
    String pae;
    String cla;
    String pla;
    String cala;
    String pala;
    String volumen;

    public GestionRutinas() {
    }

    public GestionRutinas(String microciclo,
                          String mesosiclo,
                          String etapa,
                          String periodo,
                          String nroSesiones,
                          String aer,
                          String ael,
                          String aem,
                          String aei,
                          String pae,
                          String cla,
                          String pla,
                          String cala,
                          String pala,
                          String volumen) {

        this.microciclo = microciclo;
        this.mesosiclo = mesosiclo;
        this.etapa = etapa;
        this.periodo = periodo;
        this.nroSesiones = nroSesiones;
        this.aer = aer;
        this.ael = ael;
        this.aem = aem;
        this.aei = aei;
        this.pae = pae;
        this.cla = cla;
        this.pla = pla;
        this.cala = cala;
        this.pala = pala;
        this.volumen = volumen;
    }

    public GestionRutinas(String microciclo, String mesosiclo, String etapa, String periodo, String nroSesiones) {
        this.microciclo = microciclo;
        this.mesosiclo = mesosiclo;
        this.etapa = etapa;
        this.periodo = periodo;
        this.nroSesiones = nroSesiones;
    }

    public String getMicrociclo() {
        return microciclo;
    }

    public String getMesosiclo() {
        return mesosiclo;
    }

    public String getEtapa() {
        return etapa;
    }

    public String getPeriodo() {
        return periodo;
    }

    public String getNroSesiones() {
        return nroSesiones;
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
}
