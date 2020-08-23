package com.rest.bshape.userhistory;

import com.rest.bshape.bodytype.BodyType;
import com.rest.bshape.meal.Meal;
import com.rest.bshape.target.Target;
import com.rest.bshape.typeofmeal.TypeOfMeal;
import com.rest.bshape.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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

    private Meal meals;

    private User users;

    private BodyType bodyTypes;

    private TypeOfMeal typeOfMeals;

    private Target targets;

}
