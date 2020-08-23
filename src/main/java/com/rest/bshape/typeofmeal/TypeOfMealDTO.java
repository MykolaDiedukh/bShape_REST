package com.rest.bshape.typeofmeal;

import com.rest.bshape.userhistory.UserHistoryDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TypeOfMealDTO {

    private Long id;

    private String typeMeals;

    private List<UserHistoryDTO> userHistories;

}
