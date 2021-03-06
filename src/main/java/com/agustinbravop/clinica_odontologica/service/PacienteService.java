package com.agustinbravop.clinica_odontologica.service;

import com.agustinbravop.clinica_odontologica.dto.PacienteDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PacienteService {
    PacienteDTO getOne(Long id);

    PacienteDTO getByDni(String dni);

    List<PacienteDTO> getAll();

    PacienteDTO update(PacienteDTO pacienteDTO);

    void delete(PacienteDTO pacienteDTO);

    void deleteById(Long id);

    PacienteDTO create(PacienteDTO pacienteDTO);
}
