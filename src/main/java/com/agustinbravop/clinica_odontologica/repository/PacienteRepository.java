package com.agustinbravop.clinica_odontologica.repository;

import com.agustinbravop.clinica_odontologica.model.Domicilio;
import com.agustinbravop.clinica_odontologica.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    @Override
    Paciente getById(Long id);

    @Override
    List<Paciente> findAll();

    @Override
    <S extends Paciente> S save(S s);

    @Override
    void delete(Paciente domicilio);

    @Override
    void deleteById(Long id);
}
