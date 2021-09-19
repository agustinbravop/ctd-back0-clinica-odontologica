package com.agustinbravop.clinica_odontologica.service.impl;

import com.agustinbravop.clinica_odontologica.dto.OdontologoDTO;
import com.agustinbravop.clinica_odontologica.model.Odontologo;
import com.agustinbravop.clinica_odontologica.repository.OdontologoRepository;
import com.agustinbravop.clinica_odontologica.service.OdontologoService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OdontologoServiceImpl implements OdontologoService {

    @Autowired
    private OdontologoRepository odontologoRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public OdontologoDTO getOne(Long id) {
        Odontologo odontologo = odontologoRepository.getById(id);
        return mapper.map(odontologo, OdontologoDTO.class);
    }

    @Override
    public List<OdontologoDTO> getAll() {
        List<Odontologo> odontologos = odontologoRepository.findAll();
        return mapper.map(
                odontologos,
                new TypeToken<List<OdontologoDTO>>() {}.getType()
        );
    }

    @Override
    public OdontologoDTO update(OdontologoDTO odontologoDTO) {
        Odontologo odontologo = mapper.map(odontologoDTO, Odontologo.class);
        odontologo = odontologoRepository.save(odontologo);
        return mapper.map(odontologo, OdontologoDTO.class);
    }

    @Override
    public void delete(OdontologoDTO odontologoDTO) {
        odontologoRepository.deleteById(odontologoDTO.getId());
    }

    @Override
    public OdontologoDTO create(OdontologoDTO odontologoDTO) {
        Odontologo odontologo = mapper.map(odontologoDTO, Odontologo.class);
        odontologo = odontologoRepository.save(odontologo);
        return mapper.map(odontologo, OdontologoDTO.class);
    }
}
