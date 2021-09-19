package com.agustinbravop.clinica_odontologica.repository;

import com.agustinbravop.clinica_odontologica.model.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DomicilioRepository extends JpaRepository<Domicilio, Long> {

    @Override
    Domicilio getById(Long id);

    @Override
    List<Domicilio> findAll();

    @Override
    <S extends Domicilio> S save(S s);

    @Override
    void delete(Domicilio domicilio);

    @Override
    void deleteById(Long id);
}
