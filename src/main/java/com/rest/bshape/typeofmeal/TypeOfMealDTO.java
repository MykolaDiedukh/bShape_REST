package com.rest.bshape.typeofmeal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TypeOfMealDTO {

    private Long id;

    private String typeMeals;

}
