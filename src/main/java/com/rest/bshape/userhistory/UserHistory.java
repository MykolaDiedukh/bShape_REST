package com.rest.bshape.userhistory;

import com.rest.bshape.bodytype.BodyType;
import com.rest.bshape.meal.Meal;
import com.rest.bshape.target.Target;
import com.rest.bshape.typeofmeal.TypeOfMeal;
import com.rest.bshape.user.domain.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
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

    @OneToMany(fetch = FetchType.EAGER)
    private List<Meal> meals;
    @OneToOne(fetch = FetchType.EAGER)
    private User users;
    @OneToOne(fetch = FetchType.EAGER)
    private BodyType bodyTypes;
    @OneToOne(fetch = FetchType.EAGER)
    private TypeOfMeal typeOfMeals;
    @OneToOne(fetch = FetchType.EAGER)
    private Target targets;

}
