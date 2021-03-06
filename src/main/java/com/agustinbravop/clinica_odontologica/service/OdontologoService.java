package com.agustinbravop.clinica_odontologica.service;

import com.agustinbravop.clinica_odontologica.dto.OdontologoDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OdontologoService {
    OdontologoDTO getOne(Long id);

    List<OdontologoDTO> getAll();

    OdontologoDTO update(OdontologoDTO odontologoDTO);

    void delete(OdontologoDTO odontologoDTO);

    void deleteById(Long id);

    OdontologoDTO create(OdontologoDTO odontologoDTO);
}
