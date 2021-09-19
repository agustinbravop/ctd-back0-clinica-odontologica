CREATE TABLE IF NOT EXISTS domicilio(
    id BIGINT auto_increment PRIMARY KEY,
    calle VARCHAR(50),
    numero INT,
    localidad VARCHAR(50),
    provincia VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS paciente(
    id BIGINT auto_increment PRIMARY KEY,
    nombre VARCHAR(30),
    apellido VARCHAR(30),
    dni INT,
    fecha_ingreso TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT fk_domicilio
        FOREIGN KEY(domicilio_id) REFERENCES domicilio(id)
);

CREATE TABLE IF NOT EXISTS odontologo(
    id BIGINT auto_increment PRIMARY KEY,
    nombre VARCHAR(30),
    apellido VARCHAR(30),
    matricula INT
);

