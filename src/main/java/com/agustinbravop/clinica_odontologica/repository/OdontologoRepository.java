package com.agustinbravop.clinica_odontologica.repository;

import com.agustinbravop.clinica_odontologica.model.Domicilio;
import com.agustinbravop.clinica_odontologica.model.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo, Long> {
    @Override
    Odontologo getById(Long id);

    @Override
    List<Odontologo> findAll();

    @Override
    <S extends Odontologo> S save(S s);

    @Override
    void delete(Odontologo domicilio);
}
