package com.example.summy.controlrendimiento.model;

/**
 * Created by SUMMY on 12/1/2017.
 */

public class DiarioDisciplina {

    String tiempoTrabajo;
    String fcMax;

    public DiarioDisciplina(String tiempoTrabajo, String fcMax) {
        this.tiempoTrabajo = tiempoTrabajo;
        this.fcMax = fcMax;
    }

    public String getTiempoTrabajo() {
        return tiempoTrabajo;
    }

    public String getFcMax() {
        return fcMax;
    }
}
