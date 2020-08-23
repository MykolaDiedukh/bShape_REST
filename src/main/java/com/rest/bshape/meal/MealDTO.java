package com.rest.bshape.meal;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MealDTO {

    private Long id;

    private String mealName;

//    private List<ProductDTO> products;
}
