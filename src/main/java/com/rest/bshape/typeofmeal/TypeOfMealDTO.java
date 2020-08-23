package com.rest.bshape.typeofmeal;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
public class TypeOfMealDTO {

    private Long id;

    private String typeMeals;

//    private List<UserHistoryDTO> userHistories;

}
