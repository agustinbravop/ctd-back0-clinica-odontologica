package com.agustinbravop.clinica_odontologica.controller;

import com.agustinbravop.clinica_odontologica.dto.OdontologoDTO;
import com.agustinbravop.clinica_odontologica.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/odontologo")
public class OdontologoController {
    @Autowired
    private OdontologoService odontologoService;

    @GetMapping("")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("odontologo");
        modelAndView.addObject("odontologos", odontologoService.getAll());
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OdontologoDTO> getOdontologo(@PathVariable("id") Long id) {
        OdontologoDTO odontologoDTO = odontologoService.getOne(id);
        return ResponseEntity.ok(odontologoDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<OdontologoDTO>> getAllOdontologos() {
        List<OdontologoDTO> odontologoDTOs = odontologoService.getAll();
        return ResponseEntity.ok(odontologoDTOs);
    }

    @PostMapping("/add")
    public ResponseEntity<OdontologoDTO> addOdontologo(@RequestBody OdontologoDTO odontologoDTO) {
        odontologoDTO = odontologoService.create(odontologoDTO);
        return ResponseEntity.ok(odontologoDTO);
    }

    @PutMapping("/modify")
    public ResponseEntity<OdontologoDTO> modifyOdontologo(@RequestBody OdontologoDTO odontologoDTO) {
        odontologoDTO = odontologoService.update(odontologoDTO);
        return ResponseEntity.ok(odontologoDTO);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<OdontologoDTO> deleteOdontologo(@PathVariable Long id) {
        odontologoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
