package com.agustinbravop.clinica_odontologica.service;

import com.agustinbravop.clinica_odontologica.dto.TurnoDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TurnoService {
    TurnoDTO getOne(Long id);

    List<TurnoDTO> getAll();

    List<TurnoDTO> getByOdontologoId(Long id);

    List<TurnoDTO> getByPacienteId(Long id);

    List<TurnoDTO> getByPacienteIdAndOdontologoId(Long pacId, Long odontId);

    TurnoDTO update(TurnoDTO turnoDTO);

    void delete(TurnoDTO turnoDTO);

    void deleteById(Long id);

    TurnoDTO create(TurnoDTO turnoDTO);
}
