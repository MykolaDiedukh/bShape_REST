package com.rest.bshape.controller;

import com.rest.bshape.entity.BodyType;
import com.rest.bshape.exeption.ResourceNotFoundException;
import com.rest.bshape.repository.BodyTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bodyType")
public class BodyTypeController {

    @Autowired
    private BodyTypeRepository bodyTypeRepository;

    @GetMapping
    public List<BodyType> findAll(){
        return this.bodyTypeRepository.findAll();
    }


    @GetMapping("/{idasqId(id)
                .orElseThrow(() -> new ResourceNotFoundException("BodyType not found with id :" + id));
    }

    @PostMapping
    public BodyType create(@RequestBody BodyType bodyType){
        return  this.bodyTypeRepository.save(bodyType);
    }


    @PutMapping("/{id}")
    public BodyType update(@RequestBody BodyType bodyType, @PathVariable("id") Long id){
        BodyType existingBodyType = this.bodyTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BodyType not found with id:"+ id));
        existingBodyType.setTypeOfBody(bodyType.getTypeOfBody());
        return this.bodyTypeRepository.save(existingBodyType);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BodyType> delete(@PathVariable("id") Long id){
        BodyType existingBodyType = this.bodyTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BodyType not found with id:"+ id));
        this.bodyTypeRepository.delete(existingBodyType);
        return ResponseEntity.ok().build();
    }
}
