package com.rest.bshape.product;

import com.rest.bshape.meal.MealDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
public class ProductDTO {

    private Long id;
    private String name;
    private Double gigajoule;
    private Double calories;
    private Double alcohol;
    private Double protein;
    private Double fat;
    private Double carbohydrates;

    private List<MealDTO> meals;
}
