package com.rest.bshape.userhistory;

import com.rest.bshape.exeption.ResourceNotFoundException;
import com.rest.bshape.user.UserHistoryRepository;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserHistoryService {

    private final UserHistoryRepository userHistoryRepository;

    public UserHistoryService(UserHistoryRepository userHistoryRepository) {
        this.userHistoryRepository = userHistoryRepository;
    }


    public List<UserHistoryDTO> findAll() {
        List<UserHistory> optionalUserHistory = this.userHistoryRepository.findAll();
        return optionalUserHistory.isEmpty() ? Collections.emptyList() : optionalUserHistory.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<UserHistoryDTO> findById(Long id) {
        Optional<UserHistory> optionalUserHistory = userHistoryRepository.findById(id);
        return optionalUserHistory.isEmpty() ? Optional.empty() : optionalUserHistory.map(this::convertToDTO);
    }

    public Optional<UserHistoryID> create(UserHistoryDTO userHistoryDTO) {
        UserHistory userHistory = this.convertFromDTO(userHistoryDTO);

        UserHistory createdUserHistory = userHistoryRepository.save(userHistory);
        val userHistoryID = new UserHistoryID(createdUserHistory.getId());
        return Optional.of(userHistoryID);
    }

    public Optional<UserHistoryDTO> update(UserHistoryDTO userHistoryDTO, Long id) {
        UserHistory userHistory = this.convertFromDTO(userHistoryDTO);

        Optional<UserHistory> userHistoryById = userHistoryRepository.findById(id);
        if (userHistoryById.isEmpty()) {
            return Optional.empty();
        }
        UserHistory existingUserHistory = userHistoryById.get();
        existingUserHistory.setAlcoholEaten(userHistory.getAlcoholEaten());
        existingUserHistory.setAlcoholSchedule(userHistory.getAlcoholSchedule());
        existingUserHistory.setBodyTypes(userHistory.getBodyTypes());
        existingUserHistory.setCaloriesEaten(userHistory.getCaloriesEaten());
        existingUserHistory.setCaloriesSchedule(userHistory.getCaloriesSchedule());
        existingUserHistory.setCarbohydratesEaten(userHistory.getCarbohydratesEaten());
        existingUserHistory.setCarbohydratesSchedule(userHistory.getCarbohydratesSchedule());
        existingUserHistory.setDate(userHistory.getDate());
        existingUserHistory.setFatEaten(userHistory.getFatEaten());
        existingUserHistory.setFatSchedule(userHistory.getFatSchedule());
        existingUserHistory.setGigajouleEaten(userHistory.getGigajouleEaten());
        existingUserHistory.setGigajouleSchedule(userHistory.getGigajouleEaten());
        existingUserHistory.setMeals(userHistory.getMeals());
        existingUserHistory.setProteinEaten(userHistory.getProteinEaten());
        existingUserHistory.setProteinSchedule(userHistory.getProteinSchedule());
        existingUserHistory.setTargets(userHistory.getTargets());
        existingUserHistory.setTypeOfMeals(userHistory.getTypeOfMeals());
        existingUserHistory.setUsers(userHistory.getUsers());
        existingUserHistory.setWeight(userHistory.getWeight());
        return Optional.of(this.convertToDTO(userHistoryRepository.save(existingUserHistory)));
    }

    public ResponseEntity<UserHistory> delete(Long id) {
        UserHistory existingUserHistory = this.userHistoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UserHistory not found with id:" + id));
        this.userHistoryRepository.delete(existingUserHistory);
        return ResponseEntity.ok().build();
    }

    private UserHistoryDTO convertToDTO(UserHistory userHistory) {
        return UserHistoryDTO.builder()
                .id(userHistory.getId())
                .alcoholEaten(userHistory.getAlcoholEaten())
                .alcoholSchedule(userHistory.getAlcoholSchedule())
//                .bodyTypeDTO(userHistory.getBodyTypes())
                .caloriesEaten(userHistory.getCaloriesEaten())
                .caloriesSchedule(userHistory.getCaloriesSchedule())
                .carbohydratesEaten(userHistory.getCarbohydratesEaten())
                .carbohydratesSchedule(userHistory.getCarbohydratesSchedule())
                .date(userHistory.getDate())
                .fatEaten(userHistory.getFatEaten())
                .fatSchedule(userHistory.getFatSchedule())
                .gigajouleEaten(userHistory.getGigajouleEaten())
                .gigajouleSchedule(userHistory.getGigajouleSchedule())
//                .mealDTO(userHistory.getMeals())
                .proteinEaten(userHistory.getProteinEaten())
                .proteinSchedule(userHistory.getProteinSchedule())
//                .targetDTO(userHistory.getTargets())
//                .typeOfMealDTO(userHistory.getTypeOfMeals())
//                .userDTO(userHistory.getUsers())
                .weight(userHistory.getWeight())
                .build();
    }

    private UserHistory convertFromDTO(UserHistoryDTO userHistoryDTO) {
        return UserHistory.builder()
                .id(userHistoryDTO.getId())
                .alcoholEaten(userHistoryDTO.getAlcoholEaten())
                .alcoholSchedule(userHistoryDTO.getAlcoholSchedule())
//                .bodyTypes(userHistoryDTO.getBodyTypeDTO())
                .caloriesEaten(userHistoryDTO.getCaloriesEaten())
                .caloriesSchedule(userHistoryDTO.getCaloriesSchedule())
                .carbohydratesEaten(userHistoryDTO.getCarbohydratesEaten())
                .carbohydratesSchedule(userHistoryDTO.getCarbohydratesSchedule())
                .date(userHistoryDTO.getDate())
                .fatEaten(userHistoryDTO.getFatEaten())
                .fatSchedule(userHistoryDTO.getFatSchedule())
                .gigajouleEaten(userHistoryDTO.getGigajouleEaten())
                .gigajouleSchedule(userHistoryDTO.getGigajouleSchedule())
//                .meals(userHistoryDTO.getMealDTO())
                .proteinEaten(userHistoryDTO.getProteinEaten())
                .proteinSchedule(userHistoryDTO.getProteinSchedule())
//                .targets(userHistoryDTO.getTargetDTO())
//                .typeOfMeals(userHistoryDTO.getTypeOfMealDTO())
//                .users(userHistoryDTO.getUserDTO())
                .weight(userHistoryDTO.getWeight())
                .build();
    }
}
