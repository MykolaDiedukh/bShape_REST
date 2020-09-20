package com.rest.bshape.bodytype;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.bshape.bodytype.domain.BodyType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@TestPropertySource(locations = "classpath:application-local.properties")
// gdzie znajduje sie moj localny properties do testow integracyjnych
@SpringBootTest // do testów integracyjnych must have adnostacja
@AutoConfigureMockMvc
// pozwala na wykonywanie testow przez controller, dobijamy sie do metod z controlera poprzez mvc, mozemy przejsc przez cale flow od controlera po repozytorium
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
// podnosi context springa przy kazdym tescie na nowo
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
// wyrzuca nam baze h2 i tworzy odnowa żeby uniknac bledu zwiazanego z id ktore juz sa zapisane w poprzednich testwach
public class BodyTypeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BodyTypeRepository bodyTypeRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldTestFindAllGetMethod() throws Exception {
        mockMvc.perform(get("/api/body-type"))
                .andExpect(status().is(200)) // spradzam czy dostane status 200
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // sprasdzamy czy dostane jsona
                .andExpect(jsonPath("$").isArray()); // główny obiekt w jsonie sprawdzamy czy jest lista
    }

    @Test
    void shouldFindBodyTypeById() throws Exception {
        bodyTypeRepository.save(new BodyType(null, "superSlim")); // towrze sobie bodyTYpe i zapisuje do dv
        mockMvc.perform(get("/api/body-type/1")) // podaje adres api
                .andExpect(status().is(200)) // status ktory oczekuje
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // sprawdzam czy json
                .andExpect(jsonPath("$.id").value(1)) // sprawdzam czy w jsonie na 1 poziomie bedzie id o wartosci 1
                .andExpect(jsonPath("$.typeOfBody").value("superSlim"));
    }

    @Test
    void shouldReturnNotFoundDuringBodyTypeId() throws Exception {
        mockMvc.perform(get("/api/body-type/50"))
                .andExpect(status().is(404))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.details").exists());
        // sprawdzam czy status 404 i czy zostanie zwrocone body z error details obiektem
    }

    @Test
    void shouldSaveBodyType() throws Exception { // poprawny zapis
        mockMvc.perform(post("/api/body-type")
                .content(objectMapper.writeValueAsBytes(new BodyType(null, "fat")))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));

    }

    @Test
    void shouldSaveBodyTypeThrowError() throws Exception { // bad request, niepoprawny dane wejsciowe
        mockMvc.perform(post("/api/body-type")
                .content(objectMapper.writeValueAsBytes(new BodyType(null, null)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400));

    }

    @Test
    void shouldUpdateBodyType() throws Exception {
        bodyTypeRepository.save(new BodyType(null, "fat"));
        mockMvc.perform(put("/api/body-type/1")
                .content(objectMapper.writeValueAsBytes(new BodyType(1L, "slim")))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.typeOfBody").value("slim"));
    }

    @Test
    void shouldNotUpdateBodyType() throws Exception {
        mockMvc.perform(put("/api/body-type/1")
                .content(objectMapper.writeValueAsBytes(new BodyType(1L, "skinny")))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404))
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.details").exists());

    }

    @Test
    void shouldDeleteBodyType() throws Exception {
        bodyTypeRepository.save(new BodyType(null, "super-slim"));
        mockMvc.perform(delete("/api/body-type/1"))
                .andExpect(status().is(200));
    }

    @Test
    void shouldNotDeleteBodyType() throws Exception {
        mockMvc.perform(delete("/api/body-type/1"))
                .andExpect(status().is(404));

    }

 /*   @Test
    void shouldReturnPage() throws Exception {
        mockMvc.perform(get("/api/body-type/page")
                .param("page","0")
                .param("page","10"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.abc").exists());
    }*/

}
