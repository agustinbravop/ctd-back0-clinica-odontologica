package com.agustinbravop.clinica_odontologica.dto;

import java.io.Serializable;

public class OdontologoDTO implements Serializable {
    private Long id;
    private String nombre;
    private String apellido;
    private Integer matricula;

    public OdontologoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }
}
