package com.agustinbravop.clinica_odontologica.model;

public class Domicilio {
    private Integer id;
    private String calle;
    private String numero;
    private String localidad;
    private String provincia;

    public Domicilio() {
    }

    public Domicilio(Integer id, String calle, String numero, String localidad, String provincia) {
        this.id = id;
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }
    public Domicilio( String calle, String numero, String localidad, String provincia) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }
}
