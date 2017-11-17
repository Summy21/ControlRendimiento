package com.example.summy.controlrendimiento.model;

/**
 * Created by SUMMY on 11/17/2017.
 */

public class CompNacional {
    String tituloComp;
    String lugarComp;
    String fechaIni;
    String fechaFin;

    public CompNacional() {
    }

    public CompNacional(String tituloComp, String lugarComp, String fechaIni, String fechaFin) {
        this.tituloComp = tituloComp;
        this.lugarComp = lugarComp;
        this.fechaIni = fechaIni;
        this.fechaFin = fechaFin;
    }

    public String getTituloComp() {
        return tituloComp;
    }

    public void setTituloComp(String tituloComp) {
        this.tituloComp = tituloComp;
    }

    public String getLugarComp() {
        return lugarComp;
    }

    public void setLugarComp(String lugarComp) {
        this.lugarComp = lugarComp;
    }

    public String getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(String fechaIni) {
        this.fechaIni = fechaIni;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }
}
