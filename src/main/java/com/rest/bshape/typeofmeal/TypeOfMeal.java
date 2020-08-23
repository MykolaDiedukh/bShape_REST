package com.rest.bshape.typeofmeal;

import com.rest.bshape.userhistory.UserHistory;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
public class TypeOfMeal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String typeMeals;

    @OneToMany(fetch = FetchType.LAZY)
    private List<UserHistory> userHistories;

}