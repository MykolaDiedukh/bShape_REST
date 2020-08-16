package com.rest.bshape.controller;

import com.rest.bshape.entity.UserHistory;
import com.rest.bshape.exeption.ResourceNotFoundException;
import com.rest.bshape.repository.UserHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userHistory")
@CrossOrigin(origins = "http://localhost:4200")
public class UserHistoryController {

    @Autowired
    private UserHistoryRepository userHistoryRepository;

    @GetMapping
    public List<UserHistory> findAll() {
        return this.userHistoryRepository.findAll();
    }


    @GetMapping("/{id}")
    public UserHistory findById(@PathVariable(value = "id") Long id) {
        return this.userHistoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UserHistory not found with id :" + id));
    }

    @PostMapping
    public UserHistory create(@RequestBody UserHistory userHistory) {
        return this.userHistoryRepository.save(userHistory);
    }


    @PutMapping("/{id}")
    public UserHistory update(@RequestBody UserHistory userHistory, @PathVariable("id") Long id) {
        UserHistory existingUserHistory = this.userHistoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UserHistory not found with id:" + id));
        existingUserHistory.setDate(userHistory.getDate());
        existingUserHistory.setWeight(userHistory.getWeight());
        existingUserHistory.setAlcoholEaten(userHistory.getAlcoholEaten());
        existingUserHistory.setAlcoholSchedule(userHistory.getAlcoholSchedule());
        existingUserHistory.setCaloriesEaten(userHistory.getCaloriesEaten());
        existingUserHistory.setCaloriesSchedule(userHistory.getCaloriesSchedule());
        existingUserHistory.setCaloriesEaten(userHistory.getCarbohydratesEaten());
        existingUserHistory.setCaloriesSchedule(userHistory.getCarbohydratesSchedule());
        existingUserHistory.setFatEaten(userHistory.getFatEaten());
        existingUserHistory.setFatSchedule(userHistory.getFatSchedule());
        existingUserHistory.setProteinEaten(userHistory.getProteinEaten());
        existingUserHistory.setProteinSchedule(userHistory.getProteinSchedule());
        return this.userHistoryRepository.save(existingUserHistory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserHistory> delete(@PathVariable("id") Long id) {
        UserHistory existingUserHistory = this.userHistoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UserHistory not found with id:" + id));
        this.userHistoryRepository.delete(existingUserHistory);
        return ResponseEntity.ok().build();
    }
}
