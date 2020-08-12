package com.rest.bshape.controller;

import com.rest.bshape.entity.Target;
import com.rest.bshape.exeption.ResourceNotFoundException;
import com.rest.bshape.repository.TargetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/target")
@CrossOrigin(origins = "http://localhost:4200")
public class TargetController {

    @Autowired
    private TargetRepository targetRepository;

    @GetMapping
    public List<Target> findAll() {
        return this.targetRepository.findAll();
    }


    @GetMapping("/{id}")
    public Target findById(@PathVariable(value = "id") Long id) {
        return this.targetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Target not found with id :" + id));
    }

    @PostMapping
    public Target create(@RequestBody Target target) {
        return this.targetRepository.save(target);
    }


    @PutMapping("/{id}")
    public Target update(@RequestBody Target target, @PathVariable("id") Long id) {
        Target existingTarget = this.targetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Target not found with id:" + id));
        existingTarget.setFutureTarget(target.getFutureTarget());
        return this.targetRepository.save(existingTarget);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Target> delete(@PathVariable("id") Long id) {
        Target existingTarget = this.targetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Target not found with id:" + id));
        this.targetRepository.delete(existingTarget);
        return ResponseEntity.ok().build();
    }
}
