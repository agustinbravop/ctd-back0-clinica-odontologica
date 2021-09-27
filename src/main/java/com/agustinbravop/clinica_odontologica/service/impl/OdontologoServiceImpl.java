package com.agustinbravop.clinica_odontologica.service.impl;

import com.agustinbravop.clinica_odontologica.dto.OdontologoDTO;
import com.agustinbravop.clinica_odontologica.exceptions.BadRequestException;
import com.agustinbravop.clinica_odontologica.exceptions.ResourceNotFoundException;
import com.agustinbravop.clinica_odontologica.model.Odontologo;
import com.agustinbravop.clinica_odontologica.repository.OdontologoRepository;
import com.agustinbravop.clinica_odontologica.service.OdontologoService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                new TypeToken<List<OdontologoDTO>>() {
                }.getType()
        );
    }

    @Override
    public OdontologoDTO update(OdontologoDTO odontologoDTO) {
        if(odontologoRepository.getById(odontologoDTO.getId()) == null){
            throw new ResourceNotFoundException("No existe odontologo con id: " + odontologoDTO.getId());
        }

        Odontologo odontologo = mapper.map(odontologoDTO, Odontologo.class);
        odontologo = odontologoRepository.save(odontologo);
        return mapper.map(odontologo, OdontologoDTO.class);
    }

    @Override
    public void delete(OdontologoDTO odontologoDTO) {
        odontologoRepository.deleteById(odontologoDTO.getId());
    }

    @Override
    public void deleteById(Long id) {
        odontologoRepository.deleteById(id);
    }

    @Override
    public OdontologoDTO create(OdontologoDTO odontologoDTO) {
        Odontologo odontologo = mapper.map(odontologoDTO, Odontologo.class);

        if(odontologo.getId() != null){
            throw new BadRequestException("No se puede crear odontologos con un id ya asignado.");
        }

        odontologo = odontologoRepository.save(odontologo);
        return mapper.map(odontologo, OdontologoDTO.class);
    }
}
