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
import java.util.Scanner;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private DomicilioRepository domicilioRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public PacienteDTO getOne(Long id) {
        Paciente paciente = pacienteRepository.getById(id);
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
        if(pacienteRepository.getById(pacienteDTO.getId()) == null){
            throw new ResourceNotFoundException("No existe paciente con id: " + pacienteDTO.getId());
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

        Long domicilioId = domicilioRepository.findByPropertiesEquality(paciente.getDomicilio());
        if (domicilioId == null) {
            // domicilio no existe, hay que guardarlo
            domicilioRepository.save(paciente.getDomicilio());
        } else {
            // domicilio ya existe (relación uno a muchos)
            paciente.getDomicilio().setId(domicilioId);
        }

        if(paciente.getId() != null){
            throw new BadRequestException("No se puede crear pacientes con un id ya asignado.");
        }

        paciente = pacienteRepository.save(paciente);
        return mapper.map(paciente, PacienteDTO.class);
    }
}
