package com.agustinbravop.clinica_odontologica.model;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Turno {
    private Integer id;
    private Paciente paciente;
    private Odontologo odontologo;
    private Date date; /*2021-09-10*/

    public Turno(Integer id, Paciente paciente, Odontologo odontologo, Date date) {
        this.id = id;
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.date = date;
    }

    public Turno() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
