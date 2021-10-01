package com.agustinbravop.clinica_odontologica.controller;

import com.agustinbravop.clinica_odontologica.dto.TurnoDTO;
import com.agustinbravop.clinica_odontologica.service.PacienteService;
import com.agustinbravop.clinica_odontologica.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/turno")
public class TurnoController {
    private final TurnoService turnoService;
    private final PacienteController pacienteController;
    private final OdontologoController odontologoController;

    @Autowired
    public TurnoController(TurnoService turnoService, PacienteController pacienteController, OdontologoController odontologoController) {
        this.turnoService = turnoService;
        this.pacienteController = pacienteController;
        this.odontologoController = odontologoController;
    }

    @GetMapping("")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("turno");
        modelAndView.addObject("turnos", turnoService.getAll());
        modelAndView.addObject("pacientes", pacienteController.getAllPacientes().getBody());
        modelAndView.addObject("odontologos", odontologoController.getAllOdontologos().getBody());

        return modelAndView;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoDTO> getTurno(@PathVariable("id") Long id) {
        TurnoDTO turnoDTO = turnoService.getOne(id);
        return ResponseEntity.ok(turnoDTO);
    }

    @GetMapping("/getBy")
    public ResponseEntity<List<TurnoDTO>> getTurnosByPacienteIdAndOdontologoId(
            @RequestParam(required = false) Long pacId,
            @RequestParam(required = false) Long odontId
    ) {
        if(pacId == null && odontId == null){
            return ResponseEntity.badRequest().build();
        }

        List<TurnoDTO> turnoDTOs;
        if (pacId == null) {
            turnoDTOs = turnoService.getByOdontologoId(odontId);
        } else if (odontId == null) {
            turnoDTOs = turnoService.getByPacienteId(pacId);
        } else {
            turnoDTOs = turnoService.getByPacienteIdAndOdontologoId(pacId, odontId);
        }
        return ResponseEntity.ok(turnoDTOs);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TurnoDTO>> getAllTurnos() {
        List<TurnoDTO> turnoDTOs = turnoService.getAll();
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
