package com.agustinbravop.clinica_odontologica.service.impl;

import com.agustinbravop.clinica_odontologica.dto.TurnoDTO;
import com.agustinbravop.clinica_odontologica.model.Turno;
import com.agustinbravop.clinica_odontologica.repository.DomicilioRepository;
import com.agustinbravop.clinica_odontologica.repository.TurnoRepository;
import com.agustinbravop.clinica_odontologica.service.TurnoService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoServiceImpl implements TurnoService {

    @Autowired
    private TurnoRepository turnoRepository;
    @Autowired
    private DomicilioRepository domicilioRepository;
    @Autowired
    private ModelMapper mapper;

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
                new TypeToken<List<TurnoDTO>>() {}.getType()
        );
    }

    @Override
    public List<TurnoDTO> getByOdontologoId(Long id) {
        List<Turno> turnos = turnoRepository.findByOdontologoId(id);
        return mapper.map(
                turnos,
                new TypeToken<List<TurnoDTO>>() {}.getType()
        );
    }

    @Override
    public List<TurnoDTO> getByPacienteId(Long id) {
        List<Turno> turnos = turnoRepository.findByPacienteId(id);
        return mapper.map(
                turnos,
                new TypeToken<List<TurnoDTO>>() {}.getType()
        );
    }

    @Override
    public List<TurnoDTO> getByPacienteIdAndOdontologoId(Long pacId, Long odontId){
        List<Turno> turnos;
        if(pacId == null){
            turnos = turnoRepository.findByOdontologoId(odontId);
        } else if(odontId == null){
            turnos = turnoRepository.findByPacienteId(pacId);
        } else {
            turnos = turnoRepository.findByPacienteIdAndOdontologoId(pacId, odontId);
        }
        return mapper.map(
                turnos,
                new TypeToken<List<TurnoDTO>>() {}.getType()
        );
    }


    @Override
    public TurnoDTO update(TurnoDTO turnoDTO) {
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
        turno = turnoRepository.save(turno);
        return mapper.map(turno, TurnoDTO.class);
    }
}