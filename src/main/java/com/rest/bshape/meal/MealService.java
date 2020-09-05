package com.rest.bshape.meal;

import com.rest.bshape.exeption.ResourceNotFoundException;
import com.rest.bshape.product.Product;
import com.rest.bshape.product.ProductDTO;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
class MealService {

    private final MealRepository mealRepository;

    public MealService(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    public List<MealDTO> findAll() {
        List<Meal> optionalMeal = this.mealRepository.findAll();
        return optionalMeal.isEmpty() ? Collections.emptyList() : optionalMeal.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<MealDTO> findById(Long id) {
        Optional<Meal> optionalUser = mealRepository.findById(id);
        return optionalUser.isEmpty() ? Optional.empty() : optionalUser.map(this::convertToDTO);
    }

    public Optional<MealID> create(MealDTO mealDTO) {
        Meal meal = this.convertFromDTO(mealDTO);
        Meal createdMeal = mealRepository.save(meal);
        val mealID = new MealID(createdMeal.getId());
        return Optional.of(mealID);
    }

    public Optional<MealDTO> update(MealDTO mealDTO, Long id) {
        Meal meal = this.convertFromDTO(mealDTO);

        Optional<Meal> mealById = mealRepository.findById(id);
        if (mealById.isEmpty()) {
            return Optional.empty();
        }
        Meal existingMeal = mealById.get();
        existingMeal.setMealName(meal.getMealName());
        existingMeal.setProducts(meal.getProducts());
        return Optional.of(this.convertToDTO(mealRepository.save(existingMeal)));
    }

    public ResponseEntity<MealID> delete(Long id) {
        Meal existingMeal = this.mealRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id:" + id));
        this.mealRepository.delete(existingMeal);
        return ResponseEntity.ok().build();
    }

    private MealDTO convertToDTO(Meal meal) {
        return MealDTO.builder()
                .id(meal.getId())
                .mealName(meal.getMealName())
//                .productDTO(meal.getProducts().stream().map(this::convertToDTO).collect(Collectors.toList()))
                .build();
    }

    private Meal convertFromDTO(MealDTO mealDTO) {
        return Meal.builder()
                .id(mealDTO.getId())
                .mealName(mealDTO.getMealName())
//                .products(mealDTO.getProductDTO().stream().map(this::convertFromDTO).collect(Collectors.toList()))
                .build();
    }

    private ProductDTO convertToDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .weight(product.getWeight())
                .alcohol(product.getAlcohol())
                .calories(product.getCalories())
                .carbohydrates(product.getCarbohydrates())
                .fat(product.getFat())
                .gigajoule(product.getGigajoule())
                .protein(product.getProtein())
                .build();
    }

    private Product convertFromDTO(ProductDTO productDTO) {
        return Product.builder()
                .id(productDTO.getId())
                .name(productDTO.getName())
                .weight(productDTO.getWeight())
                .alcohol(productDTO.getAlcohol())
                .calories(productDTO.getCalories())
                .carbohydrates(productDTO.getCarbohydrates())
                .fat(productDTO.getFat())
                .gigajoule(productDTO.getGigajoule())
                .protein(productDTO.getProtein())
                .build();
    }
}
