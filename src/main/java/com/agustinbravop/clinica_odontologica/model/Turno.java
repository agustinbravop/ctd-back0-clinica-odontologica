package com.agustinbravop.clinica_odontologica.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private Date fecha; /* YYYY-MM-DD */

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "odontologo_id")
    private Odontologo odontologo;

    public Turno(Long id, Paciente paciente, Odontologo odontologo, Date fecha) {
        this.id = id;
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fecha = fecha;
    }

    public Turno() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
