package com.example.summy.controlrendimiento.model;

/**
 * Created by SUMMY on 12/1/2017.
 */

public class DiarioDisciplina {

    String tiempoTrabajo;
    String fcMax;
    String volGral;
    String fecha;

    public DiarioDisciplina(String tiempoTrabajo, String fcMax, String volGral, String fecha) {
        this.tiempoTrabajo = tiempoTrabajo;
        this.fcMax = fcMax;
        this.volGral = volGral;
        this.fecha = fecha;
    }

    public String getTiempoTrabajo() {
        return tiempoTrabajo;
    }

    public String getFcMax() {
        return fcMax;
    }

    public String getVolGral() {
        return volGral;
    }

    public String getFecha() {
        return fecha;
    }
}
