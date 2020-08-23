package com.rest.bshape.target;

import com.rest.bshape.exeption.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/target")
@CrossOrigin(origins = "http://localhost:4200")
public class TargetController {

    private final TargetService targetService;

    public TargetController(TargetService targetService) {
        this.targetService = targetService;
    }

    @GetMapping
    public List<TargetDTO> findAll() {
        return this.targetService.findAll();
    }


    @GetMapping("/{id}")
    public TargetDTO findById(@PathVariable(value = "id") Long id) {
        return targetService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Target not found with id :" + id));
    }

    @PostMapping
    public TargetID create(@RequestBody TargetDTO targetDTO) {
        return targetService.create(targetDTO).orElseThrow(() -> new ResourceNotFoundException("Target not created"));
    }


    @PutMapping("/{id}")
    public TargetDTO update(@RequestBody TargetDTO targetDTO, @PathVariable("id") Long id) {
        return targetService.update(targetDTO, id).orElseThrow(() -> new ResourceNotFoundException("Target not found with id :" + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Target> delete(@PathVariable("id") Long id) {
        return this.targetService.delete(id);
    }
}
