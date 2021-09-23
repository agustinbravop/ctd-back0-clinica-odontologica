package com.agustinbravop.clinica_odontologica.repository;

import com.agustinbravop.clinica_odontologica.model.Odontologo;
import com.agustinbravop.clinica_odontologica.model.Paciente;
import com.agustinbravop.clinica_odontologica.model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {
    @Override
    Turno getById(Long id);

    @Override
    List<Turno> findAll();

    @Override
    <S extends Turno> S save(S s);

    @Override
    void delete(Turno domicilio);

    @Override
    void deleteById(Long id);

    @Query("select t from turno t where t.paciente.id = ?1")
    List<Turno> findByPacienteId(Long id);

    @Query("select t from turno t where t.odontologo.id = ?1")
    List<Turno> findByOdontologoId(Long id);

    @Query("select t from turno t where t.paciente.id = ?1 and t.odontologo.id = ?2")
    List<Turno> findByPacienteIdAndOdontologoId(Long pacId, Long odontId);
}
