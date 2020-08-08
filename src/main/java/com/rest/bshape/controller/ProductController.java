package com.rest.bshape.controller;

import com.rest.bshape.entity.Meal;
import com.rest.bshape.exeption.ResourceNotFoundException;
import com.rest.bshape.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meal")
public class ProductController {

    @Autowired
    private MealRepository mealRepository;

    @GetMapping
    public List<Meal> findAll(){
        return this.mealRepository.findAll();
    }


    @GetMapping("/{id}")
    public Meal findById(@PathVariable(value = "id") Long id){
        return this.mealRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Meal not found with id :" + id));
    }

    @PostMapping
    public Meal create(@RequestBody Meal meal){
        return  this.mealRepository.save(meal);
    }


    @PutMapping("/{id}")
    public Meal update(@RequestBody Meal meal, @PathVariable("id") Long id){
        Meal existingMeal = this.mealRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Meal not found with id:"+ id));
        existingMeal.setMealName(meal.getMealName());
        return this.mealRepository.save(existingMeal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Meal> delete(@PathVariable("id") Long id){
        Meal existingMeal = this.mealRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Meal not found with id:"+ id));
        this.mealRepository.delete(existingMeal);
        return ResponseEntity.ok().build();
    }
}
