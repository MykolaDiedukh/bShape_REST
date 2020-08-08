package com.rest.bshape.entity;

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
    private Double caloriesEaten;
    private Double caloriesSchedule;

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