package com.rest.bshape.typeofmeal;

import com.rest.bshape.sevices.GenericService;
import com.rest.bshape.user.User;
import com.rest.bshape.exeption.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeOfMealService implements GenericService<TypeOfMeal> {

    @Autowired
    private TypeOfMealRepository typeOfMealRepository;

    @Override
    public List<TypeOfMeal> findAll() {
        return this.typeOfMealRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return this.typeOfMealRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Type of meal not found with id: " + id));
    }

    @Override
    public TypeOfMeal create(TypeOfMeal typeOfMeal) {
        return this.typeOfMealRepository.save(typeOfMeal);
    }

    @Override
    public TypeOfMeal update(TypeOfMeal typeOfMeal, Long id) {
        TypeOfMeal existingMeal = this.typeOfMealRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Type of meal was not found, with id: " + id));
        existingMeal.setTypeMeals(typeOfMeal.getTypeMeals());
        return this.typeOfMealRepository.save(existingMeal);
    }

    @Override
    public ResponseEntity<TypeOfMeal> delete(Long id) {
        TypeOfMeal existingMeal = this.typeOfMealRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Type of meal was not found, with id: " + id));
        this.typeOfMealRepository.delete(existingMeal);
        return ResponseEntity.ok().build();
    }
}
