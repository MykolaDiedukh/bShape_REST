package com.rest.bshape.typeofmeal;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface TypeOfMealRepository extends JpaRepository<TypeOfMeal, Long> {
}
