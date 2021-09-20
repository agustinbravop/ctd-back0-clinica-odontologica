package com.agustinbravop.clinica_odontologica.controller;

import com.agustinbravop.clinica_odontologica.dto.PacienteDTO;
import com.agustinbravop.clinica_odontologica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> getPaciente(@PathVariable("id") Long id){
        PacienteDTO pacienteDTO = pacienteService.getOne(id);
        return ResponseEntity.ok(pacienteDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PacienteDTO>> getAllPacientes(){
        List<PacienteDTO> pacienteDTOs = pacienteService.getAll();
        return ResponseEntity.ok(pacienteDTOs);
    }

    @PostMapping("/add")
    public ResponseEntity<PacienteDTO> addPaciente(@RequestBody PacienteDTO pacienteDTO){
        pacienteDTO = pacienteService.create(pacienteDTO);
        return ResponseEntity.ok(pacienteDTO);
    }

    @PutMapping("/modify")
    public ResponseEntity<PacienteDTO> modifyPaciente(@RequestBody PacienteDTO pacienteDTO){
        pacienteDTO = pacienteService.update(pacienteDTO);
        return ResponseEntity.ok(pacienteDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<PacienteDTO> deletePaciente(@PathVariable Long id){
        pacienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
