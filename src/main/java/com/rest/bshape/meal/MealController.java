package com.rest.bshape.meal;

import com.rest.bshape.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meal")
@CrossOrigin(origins = "http://localhost:4200")
class MealController {

    private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping
    public List<MealDTO> findAll() {
        return this.mealService.findAll();
    }


    @GetMapping("/{id}")
    public MealDTO findById(@PathVariable(value = "id") Long id) {
        return mealService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Meal not found with id :" + id));
    }

    @PostMapping
    public MealID create(@RequestBody MealDTO mealDTO) {
        return mealService.create(mealDTO).orElseThrow(() -> new ResourceNotFoundException("Meal not created"));
    }


    @PutMapping("/{id}")
    public MealDTO update(@RequestBody MealDTO mealDTO, @PathVariable("id") Long id) {
        return mealService.update(mealDTO, id).orElseThrow(() -> new ResourceNotFoundException("Meal not found with id :" + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MealID> delete(@PathVariable("id") Long id) {
        return this.mealService.delete(id);
    }
}
