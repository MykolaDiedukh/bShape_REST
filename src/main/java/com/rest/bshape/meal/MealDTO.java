package com.rest.bshape.meal;

import com.rest.bshape.product.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MealDTO {

    private Long id;

    private String mealName;

    private List<ProductDTO> productDTO;
}
