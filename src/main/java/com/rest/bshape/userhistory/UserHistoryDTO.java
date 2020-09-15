package com.rest.bshape.userhistory;

import com.rest.bshape.bodytype.domain.BodyTypeDTO;
import com.rest.bshape.meal.MealDTO;
import com.rest.bshape.target.TargetDTO;
import com.rest.bshape.typeofmeal.TypeOfMealDTO;
import com.rest.bshape.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserHistoryDTO {

    private Long id;

    private LocalDate date;
    private Double weight;
    private Double alcoholEaten;
    private Double alcoholSchedule;
    private Double caloriesEaten;
    private Double caloriesSchedule;
    private Double carbohydratesEaten;
    private Double carbohydratesSchedule;
    private Double fatEaten;
    private Double fatSchedule;
    private Double gigajouleEaten;
    private Double gigajouleSchedule;
    private Double proteinEaten;
    private Double proteinSchedule;

    private List<MealDTO> mealDTO;

    private UserDTO userDTO;

    private BodyTypeDTO bodyTypeDTO;

    private TypeOfMealDTO typeOfMealDTO;

    private TargetDTO targetDTO;

}
