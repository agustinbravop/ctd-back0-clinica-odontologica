package com.agustinbravop.clinica_odontologica.odontologo;

import com.agustinbravop.clinica_odontologica.config.ApplicationConfig;
import com.agustinbravop.clinica_odontologica.dto.OdontologoDTO;
import com.agustinbravop.clinica_odontologica.model.Odontologo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

public class OdontologoDtoMappingTest {

    private final ModelMapper mapper = new ApplicationConfig().getModelMapper();

    private Odontologo getSampleOdontologo() {
        return new Odontologo(
                7L,
                "Agustín",
                "Bravo",
                54321
        );
    }

    private OdontologoDTO getSampleOdontologoDTO() {
        OdontologoDTO odontoDTO = new OdontologoDTO();
        odontoDTO.setId(7L);
        odontoDTO.setNombre("Agustín");
        odontoDTO.setApellido("Bravo");
        odontoDTO.setMatricula(54321);
        return odontoDTO;
    }

    static public void assertOdontologoMappingIsCorrect(Odontologo odonto, OdontologoDTO odontoDTO) {
        Assertions.assertEquals(odonto.getId(), odontoDTO.getId());
        Assertions.assertEquals(odonto.getNombre(), odontoDTO.getNombre());
        Assertions.assertEquals(odonto.getApellido(), odontoDTO.getApellido());
        Assertions.assertEquals(odonto.getMatricula(), odontoDTO.getMatricula());
    }

    @Test
    void map_fromOdontologo_toOdontologoDTO() {
        Odontologo odonto = getSampleOdontologo();
        OdontologoDTO odontoDTO = mapper.map(odonto, OdontologoDTO.class);

        assertOdontologoMappingIsCorrect(odonto, odontoDTO);
    }

    @Test
    void map_fromOdontologoDTO_toOdontologo() {
        OdontologoDTO odontoDTO = getSampleOdontologoDTO();
        Odontologo odonto = mapper.map(odontoDTO, Odontologo.class);

        assertOdontologoMappingIsCorrect(odonto, odontoDTO);
    }
}
