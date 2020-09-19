package com.rest.bshape.bodytype;

import com.rest.bshape.bodytype.domain.BodyType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@TestPropertySource(locations = "classpath:application-local.properties")
// gdzie znajduje sie moj localny properties do testow integracyjnych
@SpringBootTest // do testów integracyjnych must have adnostacja
@AutoConfigureMockMvc
// pozwala na wykonywanie testow przez controller, dobijamy sie do metod z controlera poprzez mvc, mozemy przejsc przez cale flow od controlera po repozytorium
public class BodyTypeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BodyTypeRepository bodyTypeRepository;

   /*@PostMapping
    public BodyTypeID create(@RequestBody BodyTypeDTO bodyTypeDTO) {
        return bodyTypeService.create(convertFromDTO(bodyTypeDTO));
    }
    }*/

    @Test
    void shouldTestFindAllGetMethod() throws Exception {
        mockMvc.perform(get("/api/body-type"))
                .andExpect(status().is(200)) // spradzam czy dostane status 200
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // sprasdzamy czy dostane jsona
                .andExpect(jsonPath("$").isArray()); // główny obiekt w jsonie sprawdzamy czy jest lista
    }

    @Test
    void shouldFindBodyTypeById() throws Exception {
        bodyTypeRepository.save(new BodyType(null, "superSlim"));
        mockMvc.perform(get("/api/body-type/1"))
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.typeOfBody").value("superSlim"));
    }

    @Test
    void shouldReturnNotFoundDuringBodyTypeId() throws Exception {
        mockMvc.perform(get("/api/body-type/1"))
                .andExpect(status().is(404))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.typeOfBody").value("superSlim"));
    }


}
