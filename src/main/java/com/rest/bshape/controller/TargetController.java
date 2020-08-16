package com.rest.bshape.controller;

import com.rest.bshape.entity.Target;
import com.rest.bshape.sevices.TargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/target")
@CrossOrigin(origins = "http://localhost:4200")
public class TargetController {

    @Autowired
    private TargetService targetService;

    @GetMapping
    public List<Target> findAll() {
        return this.targetService.findAll();
    }


    @GetMapping("/{id}")
    public Target findById(@PathVariable(value = "id") Long id) {
        return this.targetService.findById(id);
    }

    @PostMapping
    public Target create(@RequestBody Target target) {
        return this.targetService.create(target);
    }


    @PutMapping("/{id}")
    public Target update(@RequestBody Target target, @PathVariable("id") Long id) {
        return this.targetService.update(target, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Target> delete(@PathVariable("id") Long id) {
        return this.targetService.delete(id);
    }
}
