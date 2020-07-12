package com.rest.bshape.controller;

import com.rest.bshape.entity.TypeOfMeals;
import com.rest.bshape.exeption.ResourceNotFoundException;
import com.rest.bshape.repository.TypeOfMealsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/typeofmeals")
public class TypeOfMealsController {

    @Autowired
    private TypeOfMealsRepository mealsRepository;

    @GetMapping
    public List<TypeOfMeals> findAll() {
        return this.mealsRepository.findAll();
    }

    @GetMapping("/{id}")
    public TypeOfMeals findById(@PathVariable(value = "id") Long id) {
        return this.mealsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Type of meal not found with id: " + id));
    }

    @PostMapping
    public TypeOfMeals create(@RequestBody TypeOfMeals typeOfMeals){
        return this.mealsRepository.save(typeOfMeals);
    }

    @PutMapping("/{id}")
    public TypeOfMeals update(@RequestBody TypeOfMeals typeOfMeals, @PathVariable(value = "id") Long id){
        TypeOfMeals existingMeal = this.mealsRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Type of meal was not found, with id: " + id));
        existingMeal.setTypeMeals(typeOfMeals.getTypeMeals());
        return this.mealsRepository.save(existingMeal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TypeOfMeals> delete(@PathVariable("id") Long id){
        TypeOfMeals existingMeal = this.mealsRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Type of meal was not found, with id: " + id));
        this.mealsRepository.delete(existingMeal);
        return ResponseEntity.ok().build();
    }
}
