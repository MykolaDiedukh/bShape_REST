package com.rest.bshape.typeofmeal;

import com.rest.bshape.exeption.ResourceNotFoundException;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TypeOfMealService {

    private final TypeOfMealRepository typeOfMealRepository;

    public TypeOfMealService(TypeOfMealRepository typeOfMealRepository) {
        this.typeOfMealRepository = typeOfMealRepository;
    }


    public List<TypeOfMealDTO> findAll() {
        List<TypeOfMeal> optionalTypeOfMeal = this.typeOfMealRepository.findAll();
        return optionalTypeOfMeal.isEmpty() ? Collections.emptyList() : optionalTypeOfMeal.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    public Optional<TypeOfMealDTO> findById(Long id) {
        Optional<TypeOfMeal> optionalTypeOfMeal = typeOfMealRepository.findById(id);
        return optionalTypeOfMeal.isEmpty() ? Optional.empty() : optionalTypeOfMeal.map(this::convertToDTO);
    }

    public Optional<TypeOfMealID> create(TypeOfMealDTO typeOfMealDTO) {
        TypeOfMeal typeOfMeal = this.convertFromDTO(typeOfMealDTO);

        TypeOfMeal createdTypeOfMeal = typeOfMealRepository.save(typeOfMeal);
        val typeOfMealID = new TypeOfMealID(createdTypeOfMeal.getId());
        return Optional.of(typeOfMealID);
    }

    public Optional<TypeOfMealDTO> update(TypeOfMealDTO typeOfMealDTO, Long id) {
        TypeOfMeal typeOfMeal = this.convertFromDTO(typeOfMealDTO);

        Optional<TypeOfMeal> typeOfMealById = typeOfMealRepository.findById(id);
        if (typeOfMealById.isEmpty()) {
            return Optional.empty();
        }
        TypeOfMeal existingTypeOfMeal = typeOfMealById.get();
        existingTypeOfMeal.setTypeMeals(typeOfMeal.getTypeMeals());
        return Optional.of(this.convertToDTO(typeOfMealRepository.save(existingTypeOfMeal)));
    }

    public ResponseEntity<TypeOfMealID> delete(Long id) {
        TypeOfMeal existingMeal = this.typeOfMealRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Type of meal was not found, with id: " + id));
        this.typeOfMealRepository.delete(existingMeal);
        return ResponseEntity.ok().build();
    }

    private TypeOfMealDTO convertToDTO(TypeOfMeal typeOfMeal) {
        return TypeOfMealDTO.builder()
                .id(typeOfMeal.getId())
                .typeMeals(typeOfMeal.getTypeMeals())
                .build();
    }

    private TypeOfMeal convertFromDTO(TypeOfMealDTO typeOfMealDTO) {
        return TypeOfMeal.builder()
                .id(typeOfMealDTO.getId())
                .typeMeals(typeOfMealDTO.getTypeMeals())
                .build();
    }
}
