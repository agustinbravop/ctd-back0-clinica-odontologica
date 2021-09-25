package com.agustinbravop.clinica_odontologica.controller;

import com.agustinbravop.clinica_odontologica.dto.TurnoDTO;
import com.agustinbravop.clinica_odontologica.model.Turno;
import com.agustinbravop.clinica_odontologica.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turno")
public class TurnoController {
    @Autowired
    private TurnoService turnoService;

    @GetMapping("/{id}")
    public ResponseEntity<TurnoDTO> getTurno(@PathVariable("id") Long id) {
        TurnoDTO turnoDTO = turnoService.getOne(id);
        return ResponseEntity.ok(turnoDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TurnoDTO>> getAllTurnos() {
        List<TurnoDTO> turnoDTOs = turnoService.getAll();
        return ResponseEntity.ok(turnoDTOs);
    }

    @GetMapping("")
    public ResponseEntity<List<TurnoDTO>> getTurnosByPacienteIdAndOdontologoId(
            @RequestParam(required = false) Long pac,
            @RequestParam(required = false) Long odont
    ) {
        List<TurnoDTO> turnoDTOs;
        if (pac == null) {
            turnoDTOs = turnoService.getByOdontologoId(odont);
        } else if (odont == null) {
            turnoDTOs = turnoService.getByPacienteId(pac);
        } else {
            turnoDTOs = turnoService.getByPacienteIdAndOdontologoId(pac, odont);
        }
        return ResponseEntity.ok(turnoDTOs);
    }

    @PostMapping("/add")
    public ResponseEntity<TurnoDTO> addTurno(@RequestBody TurnoDTO turnoDTO) {
        turnoDTO = turnoService.create(turnoDTO);
        return ResponseEntity.ok(turnoDTO);
    }

    @PutMapping("/modify")
    public ResponseEntity<TurnoDTO> modifyTurno(@RequestBody TurnoDTO turnoDTO) {
        turnoDTO = turnoService.update(turnoDTO);
        return ResponseEntity.ok(turnoDTO);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<TurnoDTO> removeTurno(@PathVariable Long id) {
        turnoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
