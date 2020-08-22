package com.rest.bshape.sevices;

import com.rest.bshape.entity.User;
import com.rest.bshape.entity.UserHistory;
import com.rest.bshape.exeption.ResourceNotFoundException;
import com.rest.bshape.repository.UserHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserHistoryService implements GenericService<UserHistory> {

    @Autowired
    private UserHistoryRepository userHistoryRepository;

    @Override
    public List<UserHistory> findAll() {
        return this.userHistoryRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return this.userHistoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UserHistory not found with id :" + id));
    }

    @Override
    public UserHistory create(UserHistory userHistory) {
        return this.userHistoryRepository.save(userHistory);
    }

    @Override
    public UserHistory update(UserHistory userHistory, Long id) {
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

    @Override
    public ResponseEntity<UserHistory> delete(Long id) {
        UserHistory existingUserHistory = this.userHistoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UserHistory not found with id:" + id));
        this.userHistoryRepository.delete(existingUserHistory);
        return ResponseEntity.ok().build();
    }
}
