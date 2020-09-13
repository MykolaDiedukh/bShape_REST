package com.rest.bshape.bodytype;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/body-type")  // linki kebab keysem i api bo to restowe i musze to oznaczyc + mozna wersje api
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
        // zamiast contruktora
class BodyTypeController {

    private final BodyTypeService bodyTypeService;

    /*public BodyTypeController(BodyTypeService bodyTypeService) {
        this.bodyTypeService = bodyTypeService;
    }*/

    @GetMapping
    public List<BodyTypeDTO> findAll() {
        return this.bodyTypeService.findAll();
    }


    @GetMapping("/{id}")
    public BodyTypeDTO findById(@PathVariable Long id) {  // nie muszę pisac value=id poniewaz nazwa zmiennej oznaczona adnotacją pathVariable jest taka sama jak nazwa zmiennej w klamnrach z 26
        return bodyTypeService.findById(id);

    }

    @PostMapping
    public BodyTypeID create(@RequestBody BodyTypeDTO bodyTypeDTO) {
        return bodyTypeService.create(bodyTypeDTO);
    }


    @PutMapping("/{id}")
    public BodyTypeDTO update(@RequestBody BodyTypeDTO bodyTypeDTO, @PathVariable Long id) {
        return bodyTypeService.update(bodyTypeDTO, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bodyTypeService.delete(id);
    }
}
