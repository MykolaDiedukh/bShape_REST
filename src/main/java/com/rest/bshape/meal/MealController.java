package com.rest.bshape.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meal")
@CrossOrigin(origins = "http://localhost:4200")
public class MealController {

    @Autowired
    private MealService mealService;

    @GetMapping
    public List<Meal> findAll() {
        return this.mealService.findAll();
    }


    @GetMapping("/{id}")
    public Meal findById(@PathVariable(value = "id") Long id) {
        return this.mealService.findById(id);
    }

    @PostMapping
    public Meal create(@RequestBody Meal meal) {
        return this.mealService.create(meal);
    }


    @PutMapping("/{id}")
    public Meal update(@RequestBody Meal meal, @PathVariable("id") Long id) {
        return this.mealService.update(meal, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Meal> delete(@PathVariable("id") Long id) {
        return this.mealService.delete(id);
    }
}
