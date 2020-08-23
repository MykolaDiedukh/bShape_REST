package com.rest.bshape.entity;

import com.rest.bshape.bodytype.BodyType;
import com.rest.bshape.meal.Meal;
import com.rest.bshape.target.Target;
import com.rest.bshape.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Setter
@Getter
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

    @ManyToOne(fetch = FetchType.EAGER)
    private Meal meals;
    @OneToOne(fetch = FetchType.EAGER)
    private User users;
    @ManyToOne(fetch = FetchType.EAGER)
    private BodyType bodyTypes;
    @ManyToOne(fetch = FetchType.EAGER)
    private TypeOfMeal typeOfMeals;
    @OneToOne(fetch = FetchType.EAGER)
    private Target targets;

}
