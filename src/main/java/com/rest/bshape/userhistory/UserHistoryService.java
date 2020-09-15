package com.rest.bshape.userhistory;

import com.rest.bshape.bodytype.domain.BodyTypeDTO;
import com.rest.bshape.exception.ResourceNotFoundException;
import com.rest.bshape.meal.Meal;
import com.rest.bshape.meal.MealDTO;
import com.rest.bshape.product.Product;
import com.rest.bshape.product.ProductDTO;
import com.rest.bshape.target.Target;
import com.rest.bshape.target.TargetDTO;
import com.rest.bshape.typeofmeal.TypeOfMeal;
import com.rest.bshape.typeofmeal.TypeOfMealDTO;
import com.rest.bshape.user.domain.User;
import com.rest.bshape.user.UserDTO;
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

    public ResponseEntity<UserHistoryID> delete(Long id) {
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
                .caloriesEaten(userHistory.getCaloriesEaten())
                .caloriesSchedule(userHistory.getCaloriesSchedule())
                .carbohydratesEaten(userHistory.getCarbohydratesEaten())
                .carbohydratesSchedule(userHistory.getCarbohydratesSchedule())
                .date(userHistory.getDate())
                .fatEaten(userHistory.getFatEaten())
                .fatSchedule(userHistory.getFatSchedule())
                .gigajouleEaten(userHistory.getGigajouleEaten())
                .gigajouleSchedule(userHistory.getGigajouleSchedule())
//                .bodyTypeDTO(this.convertToDTO(userHistory.getBodyTypes()))
//                .mealDTO(userHistory.getMeals().stream().map(this::convertToDTO).collect(Collectors.toList()))
//                .tar eOfMealDTO(this.convertToDTO(userHistory.getTypeOfMeals()))
//                .userDTO(this.convertToDTO(userHistory.getUsers()))
                .proteinEaten(userHistory.getProteinEaten())
                .proteinSchedule(userHistory.getProteinSchedule())
                .weight(userHistory.getWeight())
                .build();
    }

    private UserHistory convertFromDTO(UserHistoryDTO userHistoryDTO) {
        return UserHistory.builder()
                .id(userHistoryDTO.getId())
                .alcoholEaten(userHistoryDTO.getAlcoholEaten())
                .alcoholSchedule(userHistoryDTO.getAlcoholSchedule())
                .caloriesEaten(userHistoryDTO.getCaloriesEaten())
                .caloriesSchedule(userHistoryDTO.getCaloriesSchedule())
                .carbohydratesEaten(userHistoryDTO.getCarbohydratesEaten())
                .carbohydratesSchedule(userHistoryDTO.getCarbohydratesSchedule())
                .date(userHistoryDTO.getDate())
                .fatEaten(userHistoryDTO.getFatEaten())
                .fatSchedule(userHistoryDTO.getFatSchedule())
                .gigajouleEaten(userHistoryDTO.getGigajouleEaten())
                .gigajouleSchedule(userHistoryDTO.getGigajouleSchedule())
                .proteinEaten(userHistoryDTO.getProteinEaten())
                .proteinSchedule(userHistoryDTO.getProteinSchedule())
//                .bodyTypes(this.convertFromDTO(userHistoryDTO.getBodyTypeDTO()))
//                .meals(userHistoryDTO.getMealDTO().stream().map(this::convertFromDTO).collect(Collectors.toList()))
//                .targets(this.convertFromDTO(userHistoryDTO.getTargetDTO()))
//                .typeOfMeals(this.convertFromDTO(userHistoryDTO.getTypeOfMealDTO()))
//                .users(this.convertFromDTO(userHistoryDTO.getUserDTO()))
                .weight(userHistoryDTO.getWeight())
                .build();
    }

    private BodyTypeDTO convertToDTO(BodyType bodyType) {
        return BodyTypeDTO.builder()
                .id(bodyType.getId())
                .typeOfBody(bodyType.getTypeOfBody())
                .build();
    }

    private BodyType convertFromDTO(BodyTypeDTO bodyTypeDTO) {
        return BodyType.builder()
                .id(bodyTypeDTO.getId())
                .typeOfBody(bodyTypeDTO.getTypeOfBody())
                .build();
    }

    private MealDTO convertToDTO(Meal meal) {
        return MealDTO.builder()
                .id(meal.getId())
                .mealName(meal.getMealName())
                .productDTO(meal.getProducts().stream().map(this::convertToDTO).collect(Collectors.toList()))
                .build();
    }

    private Meal convertFromDTO(MealDTO mealDTO) {
        return Meal.builder()
                .id(mealDTO.getId())
                .mealName(mealDTO.getMealName())
                .products(mealDTO.getProductDTO().stream().map(this::convertFromDTO).collect(Collectors.toList()))
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

    private TargetDTO convertToDTO(Target target) {
        return TargetDTO.builder()
                .id(target.getId())
                .futureTarget(target.getFutureTarget())
                .build();
    }

    private Target convertFromDTO(TargetDTO targetDTO) {
        return Target.builder()
                .id(targetDTO.getId())
                .futureTarget(targetDTO.getFutureTarget())
                .build();
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

    private UserDTO convertToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .height(user.getHeight())
                .age(user.getAge())
                .password(user.getPassword())
                .sex(user.getSex())
                .weight(user.getWeight())
                .bodyTypeDTO(this.convertToDTO(BodyType.builder().build()))
                .build();
    }

    private User convertFromDTO(UserDTO userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .email(userDTO.getEmail())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .height(userDTO.getHeight())
                .age(userDTO.getAge())
                .password(userDTO.getPassword())
                .sex(userDTO.getSex())
                .weight(userDTO.getWeight())
                .bodyType(this.convertFromDTO(BodyTypeDTO.builder().build()))
                .build();
    }
}
