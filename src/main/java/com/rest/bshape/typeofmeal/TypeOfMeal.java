package com.rest.bshape.typeofmeal;

import com.rest.bshape.userhistory.UserHistory;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TypeOfMeal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String typeMeals;
//
//    @OneToMany(fetch = FetchType.LAZY)
//    private List<UserHistory> userHistories;

}
