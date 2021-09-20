package com.agustinbravop.clinica_odontologica.service.impl;

import com.agustinbravop.clinica_odontologica.dto.PacienteDTO;
import com.agustinbravop.clinica_odontologica.model.Paciente;
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

    @Autowired
    private PacienteRepository pacienteRepository;
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
                new TypeToken<List<PacienteDTO>>() {}.getType()
        );
    }

    @Override
    public PacienteDTO update(PacienteDTO pacienteDTO) {
        Paciente paciente = mapper.map(pacienteDTO, Paciente.class);
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
        System.out.println(paciente);
        if(paciente.getFechaIngreso() == null){
            paciente.setFechaIngreso(new Date());
        }
        paciente = pacienteRepository.save(paciente);
        return mapper.map(paciente, PacienteDTO.class);
    }
}
