package com.agustinbravop.clinica_odontologica.service.impl;

import com.agustinbravop.clinica_odontologica.dto.PacienteDTO;
import com.agustinbravop.clinica_odontologica.exceptions.BadRequestException;
import com.agustinbravop.clinica_odontologica.exceptions.ResourceNotFoundException;
import com.agustinbravop.clinica_odontologica.model.Paciente;
import com.agustinbravop.clinica_odontologica.repository.DomicilioRepository;
import com.agustinbravop.clinica_odontologica.repository.PacienteRepository;
import com.agustinbravop.clinica_odontologica.service.PacienteService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;
    private final DomicilioRepository domicilioRepository;
    private final ModelMapper mapper;

    @Autowired
    public PacienteServiceImpl(PacienteRepository pacienteRepository, DomicilioRepository domicilioRepository, ModelMapper mapper) {
        this.pacienteRepository = pacienteRepository;
        this.domicilioRepository = domicilioRepository;
        this.mapper = mapper;
    }

    @Override
    public PacienteDTO getOne(Long id) {
        Paciente paciente = pacienteRepository.getById(id);
        return mapper.map(paciente, PacienteDTO.class);
    }

    @Override
    public PacienteDTO getByDni(String dni) {
        Paciente paciente = pacienteRepository.findByDni(dni);
        return mapper.map(paciente, PacienteDTO.class);
    }

    @Override
    public List<PacienteDTO> getAll() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        return mapper.map(
                pacientes,
                new TypeToken<List<PacienteDTO>>() {
                }.getType()
        );
    }

    @Override
    public PacienteDTO update(PacienteDTO pacienteDTO) {
        Paciente prevPaciente = pacienteRepository.getById(pacienteDTO.getId());
        if (prevPaciente == null) {
            throw new ResourceNotFoundException("No existe paciente con id: " + pacienteDTO.getId());
        }
        if (pacienteDTO.getFechaIngreso() == null) {
            pacienteDTO.setFechaIngreso(prevPaciente.getFechaIngreso());
        }

        Paciente paciente = mapper.map(pacienteDTO, Paciente.class);
        domicilioRepository.save(paciente.getDomicilio());
        paciente = pacienteRepository.save(paciente);
        return mapper.map(paciente, PacienteDTO.class);
    }

    @Override
    public void delete(PacienteDTO pacienteDTO) {
        pacienteRepository.deleteById(pacienteDTO.getId());
    }

    @Override
    public void deleteById(Long id) {
        pacienteRepository.deleteById(id);
    }

    @Override
    public PacienteDTO create(PacienteDTO pacienteDTO) {
        Paciente paciente = mapper.map(pacienteDTO, Paciente.class);
        if (paciente.getFechaIngreso() == null) {
            paciente.setFechaIngreso(new Date());
        }

        if (paciente.getId() != null) {
            throw new BadRequestException("No se puede crear pacientes con un id ya asignado.");
        }

        paciente = pacienteRepository.save(paciente);
        return mapper.map(paciente, PacienteDTO.class);
    }
}
