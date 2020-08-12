package com.rest.bshape.controller;

import com.rest.bshape.entity.TypeOfMeal;
import com.rest.bshape.exeption.ResourceNotFoundException;
import com.rest.bshape.repository.TypeOfMealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/typeOfMeals")
@CrossOrigin(origins = "http://localhost:4200")
public class TypeOfMealController {

    @Autowired
    private TypeOfMealRepository mealsRepository;

    @GetMapping
    public List<TypeOfMeal> findAll() {
        return this.mealsRepository.findAll();
    }

    @GetMapping("/{id}")
    public TypeOfMeal findById(@PathVariable(value = "id") Long id) {
        return this.mealsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Type of meal not found with id: " + id));
    }

    @PostMapping
    public TypeOfMeal create(@RequestBody TypeOfMeal typeOfMeal){
        return this.mealsRepository.save(typeOfMeal);
    }

    @PutMapping("/{id}")
    public TypeOfMeal update(@RequestBody TypeOfMeal typeOfMeal, @PathVariable(value = "id") Long id){
        TypeOfMeal existingMeal = this.mealsRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Type of meal was not found, with id: " + id));
        existingMeal.setTypeMeals(typeOfMeal.getTypeMeals());
        return this.mealsRepository.save(existingMeal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TypeOfMeal> delete(@PathVariable("id") Long id){
        TypeOfMeal existingMeal = this.mealsRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Type of meal was not found, with id: " + id));
        this.mealsRepository.delete(existingMeal);
        return ResponseEntity.ok().build();
    }
}
