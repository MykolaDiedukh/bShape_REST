package com.rest.bshape.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TypeOfMeals {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String typeMeals;


    public Long getId() {
        return id;
    }

    public TypeOfMeals setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTypeMeals() {
        return typeMeals;
    }

    public TypeOfMeals setTypeMeals(String typeMeals) {
        this.typeMeals = typeMeals;
        return this;
    }

    @Override
    public String toString() {
        return "TypeOfMeals{" +
                "id=" + id +
                ", typeMeals='" + typeMeals + '\'' +
                '}';
    }
}
