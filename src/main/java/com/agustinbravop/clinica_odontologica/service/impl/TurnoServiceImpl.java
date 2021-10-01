package com.agustinbravop.clinica_odontologica.service.impl;

import com.agustinbravop.clinica_odontologica.dto.TurnoDTO;
import com.agustinbravop.clinica_odontologica.exceptions.BadRequestException;
import com.agustinbravop.clinica_odontologica.exceptions.ResourceNotFoundException;
import com.agustinbravop.clinica_odontologica.model.Turno;
import com.agustinbravop.clinica_odontologica.repository.OdontologoRepository;
import com.agustinbravop.clinica_odontologica.repository.PacienteRepository;
import com.agustinbravop.clinica_odontologica.repository.TurnoRepository;
import com.agustinbravop.clinica_odontologica.service.TurnoService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoServiceImpl implements TurnoService {

    private final TurnoRepository turnoRepository;
    private final PacienteRepository pacienteRepository;
    private final OdontologoRepository odontologoRepository;
    private final ModelMapper mapper;

    @Autowired
    public TurnoServiceImpl(TurnoRepository turnoRepository, PacienteRepository pacienteRepository, OdontologoRepository odontologoRepository, ModelMapper mapper) {
        this.turnoRepository = turnoRepository;
        this.pacienteRepository = pacienteRepository;
        this.odontologoRepository = odontologoRepository;
        this.mapper = mapper;
    }

    @Override
    public TurnoDTO getOne(Long id) {
        Turno turno = turnoRepository.getById(id);
        return mapper.map(turno, TurnoDTO.class);
    }

    @Override
    public List<TurnoDTO> getAll() {
        List<Turno> turnos = turnoRepository.findAll();
        return mapper.map(
                turnos,
                new TypeToken<List<TurnoDTO>>() {
                }.getType()
        );
    }

    @Override
    public List<TurnoDTO> getByOdontologoId(Long id) {
        List<Turno> turnos = turnoRepository.findByOdontologoId(id);
        return mapper.map(
                turnos,
                new TypeToken<List<TurnoDTO>>() {
                }.getType()
        );
    }

    @Override
    public List<TurnoDTO> getByPacienteId(Long id) {
        List<Turno> turnos = turnoRepository.findByPacienteId(id);
        return mapper.map(
                turnos,
                new TypeToken<List<TurnoDTO>>() {
                }.getType()
        );
    }

    @Override
    public List<TurnoDTO> getByPacienteIdAndOdontologoId(Long pacId, Long odontId) {
        List<Turno> turnos = turnoRepository.findByPacienteIdAndOdontologoId(pacId, odontId);

        return mapper.map(
                turnos,
                new TypeToken<List<TurnoDTO>>() {
                }.getType()
        );
    }


    @Override
    public TurnoDTO update(TurnoDTO turnoDTO) {
        Turno prevTurno = turnoRepository.getById(turnoDTO.getId());
        if (prevTurno == null) {
            throw new ResourceNotFoundException("No existe turno con id: " + turnoDTO.getId());
        }
        if(turnoDTO.getFecha() == null){
            turnoDTO.setFecha(prevTurno.getFecha());
        }

        Turno turno = mapper.map(turnoDTO, Turno.class);
        turno = turnoRepository.save(turno);
        return mapper.map(turno, TurnoDTO.class);
    }

    @Override
    public void delete(TurnoDTO turnoDTO) {
        turnoRepository.deleteById(turnoDTO.getId());
    }

    @Override
    public void deleteById(Long id) {
        turnoRepository.deleteById(id);
    }

    @Override
    public TurnoDTO create(TurnoDTO turnoDTO) {
        Turno turno = mapper.map(turnoDTO, Turno.class);

        if (turno.getId() != null) {
            throw new BadRequestException("No se puede crear turnos con un id ya asignado.");
        }

        turno = turnoRepository.save(turno);
        turno.setPaciente(pacienteRepository.getById(turno.getPaciente().getId()));
        turno.setOdontologo(odontologoRepository.getById(turno.getOdontologo().getId()));
        return mapper.map(turno, TurnoDTO.class);
    }
}
