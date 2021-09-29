package com.agustinbravop.clinica_odontologica.service.impl;

import com.agustinbravop.clinica_odontologica.dto.TurnoDTO;
import com.agustinbravop.clinica_odontologica.exceptions.BadRequestException;
import com.agustinbravop.clinica_odontologica.exceptions.ResourceNotFoundException;
import com.agustinbravop.clinica_odontologica.model.Turno;
import com.agustinbravop.clinica_odontologica.repository.TurnoRepository;
import com.agustinbravop.clinica_odontologica.service.OdontologoService;
import com.agustinbravop.clinica_odontologica.service.PacienteService;
import com.agustinbravop.clinica_odontologica.service.TurnoService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoServiceImpl implements TurnoService {

    @Autowired private TurnoRepository turnoRepository;
    @Autowired private PacienteService pacienteService;
    @Autowired private OdontologoService odontologoService;
    @Autowired private ModelMapper mapper;

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
        if(turnoRepository.getById(turnoDTO.getId()) == null){
            throw new ResourceNotFoundException("No existe turno con id: " + turnoDTO.getId());
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

        if(turno.getId() != null){
            throw new BadRequestException("No se puede crear turnos con un id ya asignado.");
        }

        turno = turnoRepository.save(turno);
        return mapper.map(turno, TurnoDTO.class);
    }
}
