package com.example.summy.controlrendimiento.model;

/**
 * Created by Nancy on 02/10/17.
 */

public class Atleta {
    String nombres;
    String paterno;
    String materno;
    String estatura;
    String genero;
    String peso;
    String telCelular;
    String domicilio;
    String telFamiliar;
    String telSeguroMedico;

    public Atleta() {

    }

    public Atleta(String nombres,
                  String paterno,
                  String materno,
                  String estatura,
                  String genero,
                  String peso,
                  String telCelular,
                  String domicilio,
                  String telFamiliar,
                  String telSeguroMedico) {
        this.nombres = nombres;
        this.paterno = paterno;
        this.materno = materno;
        this.estatura = estatura;
        this.genero = genero;
        this.peso = peso;
        this.telCelular = telCelular;
        this.domicilio = domicilio;
        this.telFamiliar = telFamiliar;
        this.telSeguroMedico = telSeguroMedico;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getEstatura() {
        return estatura;
    }

    public void setEstatura(String estatura) {
        this.estatura = estatura;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getTelCelular() {
        return telCelular;
    }

    public void setTelCelular(String telCelular) {
        this.telCelular = telCelular;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getTelFamiliar() {
        return telFamiliar;
    }

    public void setTelFamiliar(String telFamiliar) {
        this.telFamiliar = telFamiliar;
    }

    public String getTelSeguroMedico() {
        return telSeguroMedico;
    }

    public void setTelSeguroMedico(String telSeguroMedico) {
        this.telSeguroMedico = telSeguroMedico;
    }
}
