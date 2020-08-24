package com.rest.bshape.meal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MealDTO {

    private Long id;

    private String mealName;

}
