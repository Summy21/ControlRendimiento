package com.example.summy.controlrendimiento.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by SUMMY on 11/17/2017.
 */

public class CompNacional {

    String tituloComp;
    String lugarComp;
    String fechaIni;
    String fechaFin;
    String key;

    public CompNacional() {
    }

    public CompNacional(String tituloComp, String lugarComp, String fechaIni, String fechaFin, String key) {
        this.tituloComp = tituloComp;
        this.lugarComp = lugarComp;
        this.fechaIni = fechaIni;
        this.fechaFin = fechaFin;
        this.key = key;
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("tituloComp", tituloComp);
        result.put("lugarComp", lugarComp);
        result.put("fechaIni", fechaIni);
        result.put("fechaFin", fechaFin);
        result.put("key", key);

        return result;
    }

}
