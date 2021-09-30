package com.agustinbravop.clinica_odontologica.odontologo;

import com.agustinbravop.clinica_odontologica.dto.OdontologoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class OdontologoIntegrationTest {

    @Autowired private MockMvc mockMvc;
    private final ObjectWriter writer = new ObjectMapper()
            .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
            .writer();

    @Test
    public void add_ResponseIsOkAndNonEmpty() throws Exception {
        OdontologoDTO odonto = new OdontologoDTO();
        odonto.setNombre("Francisco");
        odonto.setApellido("Gerardi");
        odonto.setMatricula(40791);
        String requestJson = writer.writeValueAsString(odonto);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/odontologo/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Assertions.assertFalse(result.getResponse().getContentAsString().isEmpty());
    }

    @Test
    public void get_InvalidId_ThrowsEntityNotFound() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/odontologo/{id}", 0)
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType("text/plain;charset=UTF-8")
                ).andReturn();
    }

}
