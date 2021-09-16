CREATE TABLE IF NOT EXISTS domicilio(
    id int auto_increment primary key,
    calle varchar(255),
    numero varchar(255),
    localidad varchar(255),
    provincia varchar(255)
);

CREATE TABLE IF NOT EXISTS paciente(
    id int auto_increment primary key,
    nombre varchar(255),
    apellido varchar(255),
    dni varchar(255),
    fecha_ingreso TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT fk_domicilio
        FOREIGN KEY(domicilio_id)
        REFERENCES domicilio(id)
);

CREATE TABLE IF NOT EXISTS odontologo(
    id int auto_increment primary key,
    nombre varchar(255),
    apellido varchar(255),
    matricula int
);

