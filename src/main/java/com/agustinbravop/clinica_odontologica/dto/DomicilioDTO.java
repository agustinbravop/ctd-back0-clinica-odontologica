package com.agustinbravop.clinica_odontologica.dto;

import java.io.Serializable;

public class DomicilioDTO implements Serializable {
    private String calle;
    private String numero;
    private String localidad;
    private String provincia;

    public DomicilioDTO() {
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}
