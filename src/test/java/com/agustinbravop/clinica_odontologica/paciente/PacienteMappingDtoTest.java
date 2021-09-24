package com.agustinbravop.clinica_odontologica.paciente;

import com.agustinbravop.clinica_odontologica.config.ApplicationConfig;
import com.agustinbravop.clinica_odontologica.dto.DomicilioDTO;
import com.agustinbravop.clinica_odontologica.dto.PacienteDTO;
import com.agustinbravop.clinica_odontologica.model.Domicilio;
import com.agustinbravop.clinica_odontologica.model.Paciente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.Date;

public class PacienteMappingDtoTest {

    private final ModelMapper mapper = new ApplicationConfig().getModelMapper();

    private Paciente getSamplePaciente() {
        return new Paciente(
                2L,
                "Agustín",
                "Bravo",
                "43434434",
                new Date(),
                new Domicilio(
                        "Sarasa",
                        "1234",
                        "Resistencia",
                        "Chaco"
                )
        );
    }

    private PacienteDTO getSamplePacienteDTO() {
        DomicilioDTO dDTO = new DomicilioDTO();
        dDTO.setCalle("Sarasa");
        dDTO.setNumero("1234");
        dDTO.setLocalidad("Resistencia");
        dDTO.setProvincia("Chaco");

        PacienteDTO pDTO = new PacienteDTO();
        pDTO.setId(2L);
        pDTO.setNombre("Agustín");
        pDTO.setApellido("Bravo");
        pDTO.setDni("43434434");
        pDTO.setFechaIngreso(new Date());
        pDTO.setDomicilio(dDTO);
        return pDTO;
    }

    private void assertDeepEquality(Paciente pac, PacienteDTO pacDTO) {
        Assertions.assertEquals(pac.getId(), pacDTO.getId());
        Assertions.assertEquals(pac.getNombre(), pacDTO.getNombre());
        Assertions.assertEquals(pac.getApellido(), pacDTO.getApellido());
        Assertions.assertEquals(pac.getDni(), pacDTO.getDni());

        Assertions.assertEquals(pac.getDomicilio().getCalle(), pacDTO.getDomicilio().getCalle());
        Assertions.assertEquals(pac.getDomicilio().getNumero(), pacDTO.getDomicilio().getNumero());
        Assertions.assertEquals(pac.getDomicilio().getLocalidad(), pacDTO.getDomicilio().getLocalidad());
        Assertions.assertEquals(pac.getDomicilio().getProvincia(), pacDTO.getDomicilio().getProvincia());
    }

    @Test
    void map_fromPaciente_toPacienteDTO() {
        Paciente pac = getSamplePaciente();
        PacienteDTO pacDTO = mapper.map(pac, PacienteDTO.class);

        assertDeepEquality(pac, pacDTO);
    }

    @Test
    void map_fromPacienteDTO_toPaciente() {
        PacienteDTO pacDTO = getSamplePacienteDTO();
        Paciente pac = mapper.map(pacDTO, Paciente.class);

        assertDeepEquality(pac, pacDTO);
    }
}
