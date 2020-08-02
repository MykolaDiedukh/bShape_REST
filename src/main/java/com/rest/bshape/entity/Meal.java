package com.rest.bshape.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Setter
@Getter
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String breakfast;
    private String secondBreakfast;
    private String lunch;
    private String dinner;
    private String afternoonSnack;
    private String supper;
//    private Long meals_of_user;
}