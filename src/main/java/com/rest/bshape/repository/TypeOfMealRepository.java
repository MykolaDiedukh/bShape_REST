package com.rest.bshape.repository;


import com.rest.bshape.entity.TypeOfMeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeOfMealRepository extends JpaRepository<TypeOfMeal, Long> {
}
