package com.agustinbravop.clinica_odontologica.turno;

import com.agustinbravop.clinica_odontologica.config.ApplicationConfig;
import com.agustinbravop.clinica_odontologica.dto.DomicilioDTO;
import com.agustinbravop.clinica_odontologica.dto.OdontologoDTO;
import com.agustinbravop.clinica_odontologica.dto.PacienteDTO;
import com.agustinbravop.clinica_odontologica.dto.TurnoDTO;
import com.agustinbravop.clinica_odontologica.model.Domicilio;
import com.agustinbravop.clinica_odontologica.model.Odontologo;
import com.agustinbravop.clinica_odontologica.model.Paciente;
import com.agustinbravop.clinica_odontologica.model.Turno;
import com.agustinbravop.clinica_odontologica.odontologo.OdontologoDtoMappingTest;
import com.agustinbravop.clinica_odontologica.paciente.PacienteDtoMappingTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.Date;

public class TurnoDtoMappingTest {
    private final ModelMapper mapper = new ApplicationConfig().getModelMapper();

    private Turno getSampleTurno() {
        Domicilio dom = new Domicilio(
                16L,
                "Natalia",
                "Narvaez",
                "Cali",
                "Colombia"
        );
        Paciente pac = new Paciente(
                58L,
                "Juana",
                "Alaniz",
                "59783021",
                new Date(1470196700000L),
                dom
        );
        Odontologo odont = new Odontologo(
                73L,
                "Guille",
                "Bearzot",
                34578
        );
        return new Turno(
                7L,
                pac,
                odont,
                new Date(1470196800000L)
        );
    }

    private TurnoDTO getSampleTurnoDTO() {
        DomicilioDTO dom = new DomicilioDTO();
        dom.setCalle("Libertad");
        dom.setNumero("106");
        dom.setLocalidad("Cali");
        dom.setProvincia("Valle del Cuaca");
        PacienteDTO pac = new PacienteDTO();
        pac.setId(58L);
        pac.setNombre("Juana");
        pac.setApellido("Alaniz");
        pac.setDni("59783021");
        pac.setFechaIngreso(new Date(1470196700000L));
        pac.setDomicilio(dom);
        OdontologoDTO odont = new OdontologoDTO();
        odont.setId(73L);
        odont.setNombre("Guille");
        odont.setApellido("Bearzot");
        odont.setMatricula(34578);
        TurnoDTO turno = new TurnoDTO();
        turno.setId(7L);
        turno.setPaciente(pac);
        turno.setOdontologo(odont);
        turno.setFecha(new Date(1470196800000L));
        return turno;
    }

    private void assertTurnoMappingIsCorrect(Turno turno, TurnoDTO turnoDTO) {
        PacienteDtoMappingTest.assertPacienteMappingIsCorrect(
                turno.getPaciente(),
                turnoDTO.getPaciente()
        );
        OdontologoDtoMappingTest.assertOdontologoMappingIsCorrect(
                turno.getOdontologo(),
                turnoDTO.getOdontologo()
        );
        Assertions.assertEquals(turno.getFecha(), turnoDTO.getFecha());
    }

    @Test
    void map_fromTurno_toTurnoDTO() {
        Turno turno = getSampleTurno();
        TurnoDTO turnoDTO = mapper.map(turno, TurnoDTO.class);

        assertTurnoMappingIsCorrect(turno, turnoDTO);
    }

    @Test
    void map_fromTurnoDTO_toTurno() {
        TurnoDTO turnoDTO = getSampleTurnoDTO();
        Turno turno = mapper.map(turnoDTO, Turno.class);

        assertTurnoMappingIsCorrect(turno, turnoDTO);
    }
}
