package com.agustinbravop.clinica_odontologica.model;

import java.util.Date;

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
}
