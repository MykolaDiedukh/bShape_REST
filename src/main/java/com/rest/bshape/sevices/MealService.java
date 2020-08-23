package com.rest.bshape.sevices;

import com.rest.bshape.entity.Meal;
import com.rest.bshape.user.User;
import com.rest.bshape.exeption.ResourceNotFoundException;
import com.rest.bshape.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MealService implements GenericService<Meal> {

    @Autowired
    private MealRepository mealRepository;

    @Override
    public List<Meal> findAll() {
        return this.mealRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return this.mealRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Meal not found with id :" + id));
    }

    @Override
    public Meal create(Meal meal) {
        return this.mealRepository.save(meal);
    }

    @Override
    public Meal update(Meal meal, Long id) {
        Meal existingMeal = this.mealRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Meal not found with id:" + id));
        existingMeal.setMealName(meal.getMealName());
        return this.mealRepository.save(existingMeal);
    }

    @Override
    public ResponseEntity<Meal> delete(Long id) {
        Meal existingMeal = this.mealRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Meal not found with id:" + id));
        this.mealRepository.delete(existingMeal);
        return ResponseEntity.ok().build();
    }
}
