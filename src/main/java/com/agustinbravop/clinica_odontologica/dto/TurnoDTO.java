package com.agustinbravop.clinica_odontologica.dto;

import com.agustinbravop.clinica_odontologica.model.Odontologo;
import com.agustinbravop.clinica_odontologica.model.Paciente;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

public class TurnoDTO implements Serializable {
    private Long id;
    private Date date;
    private PacienteDTO paciente;
    private OdontologoDTO odontologo;

    public TurnoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public PacienteDTO getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteDTO paciente) {
        this.paciente = paciente;
    }

    public OdontologoDTO getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(OdontologoDTO odontologo) {
        this.odontologo = odontologo;
    }
}
