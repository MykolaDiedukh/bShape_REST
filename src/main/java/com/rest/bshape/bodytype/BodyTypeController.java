package com.rest.bshape.bodytype;

import com.rest.bshape.bodytype.domain.BodyType;
import com.rest.bshape.bodytype.domain.BodyTypeDTO;
import com.rest.bshape.bodytype.domain.BodyTypeID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.rest.bshape.bodytype.converter.BodyTypeConverter.*;

@RestController
@RequestMapping("/api/body-type")  // linki kebab keysem i api bo to restowe i musze to oznaczyc + mozna wersje api
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
        // zamiast contruktora
class BodyTypeController {

    private final BodyTypeService bodyTypeService;


    @GetMapping
    public List<BodyTypeDTO> findAll() {

        return mapToListDto (bodyTypeService.findAll());
    }


    @GetMapping("/{id}")
    public BodyTypeDTO findById(@PathVariable Long id) {  // nie muszę pisac value=id poniewaz nazwa zmiennej oznaczona adnotacją pathVariable jest taka sama jak nazwa zmiennej w klamnrach z 26
        return convertToDTO(bodyTypeService.findById(id));

    }

    @PostMapping
    public BodyTypeID create(@RequestBody BodyTypeDTO bodyTypeDTO) {
        return bodyTypeService.create(convertFromDTO(bodyTypeDTO));
    }


    @PutMapping("/{id}")
    public BodyTypeDTO update(@RequestBody BodyTypeDTO bodyTypeDTO, @PathVariable Long id) {
        return convertToDTO(bodyTypeService.update(convertFromDTO(bodyTypeDTO),id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bodyTypeService.delete(id);
    }
}
