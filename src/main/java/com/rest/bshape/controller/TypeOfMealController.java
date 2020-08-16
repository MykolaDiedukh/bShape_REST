package com.rest.bshape.controller;

import com.rest.bshape.entity.TypeOfMeal;
import com.rest.bshape.exeption.ResourceNotFoundException;
import com.rest.bshape.repository.TypeOfMealRepository;
import com.rest.bshape.sevices.TypeOfMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/typeOfMeals")
@CrossOrigin(origins = "http://localhost:4200")
public class TypeOfMealController {

    @Autowired
    private TypeOfMealService typeOfMealService;

    @GetMapping
    public List<TypeOfMeal> findAll() {
        return this.typeOfMealService.findAll();
    }

    @GetMapping("/{id}")
    public TypeOfMeal findById(@PathVariable(value = "id") Long id) {
        return this.typeOfMealService.findById(id);
    }

    @PostMapping
    public TypeOfMeal create(@RequestBody TypeOfMeal typeOfMeal){
        return this.typeOfMealService.create(typeOfMeal);
    }

    @PutMapping("/{id}")
    public TypeOfMeal update(@RequestBody TypeOfMeal typeOfMeal, @PathVariable(value = "id") Long id){
        return this.typeOfMealService.update(typeOfMeal,id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TypeOfMeal> delete(@PathVariable("id") Long id){
return this.typeOfMealService.delete(id);
    }
}
