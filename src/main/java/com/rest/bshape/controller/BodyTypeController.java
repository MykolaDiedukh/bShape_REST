package com.rest.bshape.controller;

import com.rest.bshape.entity.BodyType;
import com.rest.bshape.sevices.BodyTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bodyType")
@CrossOrigin(origins = "http://localhost:4200")
public class BodyTypeController {

    @Autowired
    private BodyTypeService bodyTypeService;

    @GetMapping
    public List<BodyType> findAll() {
        return this.bodyTypeService.findAll();
    }


    @GetMapping("/{id}")
    public BodyType findById(@PathVariable(value = "id") Long id) {
        return this.bodyTypeService.findById(id);
    }

    @PostMapping
    public BodyType create(@RequestBody BodyType bodyType) {
        return this.bodyTypeService.create(bodyType);
    }


    @PutMapping("/{id}")
    public BodyType update(@RequestBody BodyType bodyType, @PathVariable("id") Long id) {
        return this.bodyTypeService.update(bodyType, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BodyType> delete(@PathVariable("id") Long id) {
        return this.bodyTypeService.delete(id);
    }
}
