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
public class TypeOfMeal {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String typeMeals;

}
