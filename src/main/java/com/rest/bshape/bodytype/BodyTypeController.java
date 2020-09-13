package com.rest.bshape.bodytype;

import com.rest.bshape.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bodyType")
@CrossOrigin(origins = "http://localhost:4200")
class BodyTypeController {

    private final BodyTypeService bodyTypeService;

    public BodyTypeController(BodyTypeService bodyTypeService) {
        this.bodyTypeService = bodyTypeService;
    }
    
    @GetMapping
    public List<BodyTypeDTO> findAll() {
        return this.bodyTypeService.findAll();
    }


    @GetMapping("/{id}")
    public BodyTypeDTO findById(@PathVariable(value = "id") Long id) {
        return bodyTypeService.findById(id);

    }

    @PostMapping
    public BodyTypeID create(@RequestBody BodyTypeDTO bodyTypeDTO) {
        return bodyTypeService.create(bodyTypeDTO).orElseThrow(() -> new ResourceNotFoundException("BodyType not created"));
    }


    @PutMapping("/{id}")
    public BodyTypeDTO update(@RequestBody BodyTypeDTO bodyTypeDTO, @PathVariable("id") Long id) {
        return bodyTypeService.update(bodyTypeDTO, id).orElseThrow(() -> new ResourceNotFoundException("BodyType not found with id :" + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BodyTypeID> delete(@PathVariable("id") Long id) {
        return this.bodyTypeService.delete(id);
    }
}
