package com.example.summy.controlrendimiento.model;

/**
 * Created by SUMMY on 11/20/2017.
 */

public class Periodo {

    String PerEtapaGeneral;
    String FechaIniEtapaGeneral;
    String FechaFinEtapaGeneral;

    String PrepEtapaEspecifica;
    String FechaIniEtapaEspecifica;
    String FechaFinEtapaEspecifica;

    String CompPerPreparatorio;
    String FechaIniPerPreparatorio;
    String FechaFinPerPreparatorio;

    String CompPerCompetencia;
    String FechaIniPerCompetencia;
    String FechaFinPerCompetencia;

    String CompPerTransicion;
    String FechaIniPerTransicion;
    String FechaFinPerTransicion;

    public Periodo() {
    }

    public Periodo(String perEtapaGeneral, String fechaIniEtapaGeneral, String fechaFinEtapaGeneral) {
        PerEtapaGeneral = perEtapaGeneral;
        FechaIniEtapaGeneral = fechaIniEtapaGeneral;
        FechaFinEtapaGeneral = fechaFinEtapaGeneral;
    }

    public Periodo(String perEtapaGeneral,
                   String fechaIniEtapaGeneral,
                   String fechaFinEtapaGeneral,
                   String prepEtapaEspecifica,
                   String fechaIniEtapaEspecifica,
                   String fechaFinEtapaEspecifica,
                   String compPerPreparatorio,
                   String fechaIniPerPreparatorio,
                   String fechaFinPerPreparatorio,
                   String compPerCompetencia,
                   String fechaIniPerCompetencia,
                   String fechaFinPerCompetencia,
                   String compPerTransicion,
                   String fechaIniPerTransicion,
                   String fechaFinPerTransicion) {

        PerEtapaGeneral = perEtapaGeneral;
        FechaIniEtapaGeneral = fechaIniEtapaGeneral;
        FechaFinEtapaGeneral = fechaFinEtapaGeneral;
        PrepEtapaEspecifica = prepEtapaEspecifica;
        FechaIniEtapaEspecifica = fechaIniEtapaEspecifica;
        FechaFinEtapaEspecifica = fechaFinEtapaEspecifica;
        CompPerPreparatorio = compPerPreparatorio;
        FechaIniPerPreparatorio = fechaIniPerPreparatorio;
        FechaFinPerPreparatorio = fechaFinPerPreparatorio;
        CompPerCompetencia = compPerCompetencia;
        FechaIniPerCompetencia = fechaIniPerCompetencia;
        FechaFinPerCompetencia = fechaFinPerCompetencia;
        CompPerTransicion = compPerTransicion;
        FechaIniPerTransicion = fechaIniPerTransicion;
        FechaFinPerTransicion = fechaFinPerTransicion;
    }

    public String getPerEtapaGeneral() {
        return PerEtapaGeneral;
    }

    public void setPerEtapaGeneral(String perEtapaGeneral) {
        PerEtapaGeneral = perEtapaGeneral;
    }

    public String getFechaIniEtapaGeneral() {
        return FechaIniEtapaGeneral;
    }

    public void setFechaIniEtapaGeneral(String fechaIniEtapaGeneral) {
        FechaIniEtapaGeneral = fechaIniEtapaGeneral;
    }

    public String getFechaFinEtapaGeneral() {
        return FechaFinEtapaGeneral;
    }

    public void setFechaFinEtapaGeneral(String fechaFinEtapaGeneral) {
        FechaFinEtapaGeneral = fechaFinEtapaGeneral;
    }

    public String getPrepEtapaEspecifica() {
        return PrepEtapaEspecifica;
    }

    public void setPrepEtapaEspecifica(String prepEtapaEspecifica) {
        PrepEtapaEspecifica = prepEtapaEspecifica;
    }

    public String getFechaIniEtapaEspecifica() {
        return FechaIniEtapaEspecifica;
    }

    public void setFechaIniEtapaEspecifica(String fechaIniEtapaEspecifica) {
        FechaIniEtapaEspecifica = fechaIniEtapaEspecifica;
    }

    public String getFechaFinEtapaEspecifica() {
        return FechaFinEtapaEspecifica;
    }

    public void setFechaFinEtapaEspecifica(String fechaFinEtapaEspecifica) {
        FechaFinEtapaEspecifica = fechaFinEtapaEspecifica;
    }

    public String getCompPerPreparatorio() {
        return CompPerPreparatorio;
    }

    public void setCompPerPreparatorio(String compPerPreparatorio) {
        CompPerPreparatorio = compPerPreparatorio;
    }

    public String getFechaIniPerPreparatorio() {
        return FechaIniPerPreparatorio;
    }

    public void setFechaIniPerPreparatorio(String fechaIniPerPreparatorio) {
        FechaIniPerPreparatorio = fechaIniPerPreparatorio;
    }

    public String getFechaFinPerPreparatorio() {
        return FechaFinPerPreparatorio;
    }

    public void setFechaFinPerPreparatorio(String fechaFinPerPreparatorio) {
        FechaFinPerPreparatorio = fechaFinPerPreparatorio;
    }

    public String getCompPerCompetencia() {
        return CompPerCompetencia;
    }

    public void setCompPerCompetencia(String compPerCompetencia) {
        CompPerCompetencia = compPerCompetencia;
    }

    public String getFechaIniPerCompetencia() {
        return FechaIniPerCompetencia;
    }

    public void setFechaIniPerCompetencia(String fechaIniPerCompetencia) {
        FechaIniPerCompetencia = fechaIniPerCompetencia;
    }

    public String getFechaFinPerCompetencia() {
        return FechaFinPerCompetencia;
    }

    public void setFechaFinPerCompetencia(String fechaFinPerCompetencia) {
        FechaFinPerCompetencia = fechaFinPerCompetencia;
    }

    public String getCompPerTransicion() {
        return CompPerTransicion;
    }

    public void setCompPerTransicion(String compPerTransicion) {
        CompPerTransicion = compPerTransicion;
    }

    public String getFechaIniPerTransicion() {
        return FechaIniPerTransicion;
    }

    public void setFechaIniPerTransicion(String fechaIniPerTransicion) {
        FechaIniPerTransicion = fechaIniPerTransicion;
    }

    public String getFechaFinPerTransicion() {
        return FechaFinPerTransicion;
    }

    public void setFechaFinPerTransicion(String fechaFinPerTransicion) {
        FechaFinPerTransicion = fechaFinPerTransicion;
    }
}
