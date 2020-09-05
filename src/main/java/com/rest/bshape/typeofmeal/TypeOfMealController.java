package com.rest.bshape.typeofmeal;

import com.rest.bshape.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/typeOfMeals")
@CrossOrigin(origins = "http://localhost:4200")
public class TypeOfMealController {

    private final TypeOfMealService typeOfMealService;

    public TypeOfMealController(TypeOfMealService typeOfMealService) {
        this.typeOfMealService = typeOfMealService;
    }

    @GetMapping
    public List<TypeOfMealDTO> findAll() {
        return this.typeOfMealService.findAll();
    }

    @GetMapping("/{id}")
    public TypeOfMealDTO findById(@PathVariable(value = "id") Long id) {
        return typeOfMealService.findById(id).orElseThrow(() -> new ResourceNotFoundException("TypeOfMeal not found with id :" + id));
    }

    @PostMapping
    public TypeOfMealID create(@RequestBody TypeOfMealDTO typeOfMealDTO) {
        return typeOfMealService.create(typeOfMealDTO).orElseThrow(() -> new ResourceNotFoundException("TypeOfMeal not created"));
    }


    @PutMapping("/{id}")
    public TypeOfMealDTO update(@RequestBody TypeOfMealDTO metypeOfMealDTOlDTO, @PathVariable("id") Long id) {
        return typeOfMealService.update(metypeOfMealDTOlDTO, id).orElseThrow(() -> new ResourceNotFoundException("TypeOfMeal not found with id :" + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TypeOfMealID> delete(@PathVariable("id") Long id) {
        return this.typeOfMealService.delete(id);
    }
}
