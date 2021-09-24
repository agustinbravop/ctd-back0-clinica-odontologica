package com.agustinbravop.clinica_odontologica.dto;

import java.io.Serializable;
import java.util.Date;

public class TurnoDTO implements Serializable {
    private Long id;
    private Date fecha;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
