package com.rest.bshape.meal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
interface MealRepository extends JpaRepository<Meal, Long> {
}
