package com.rest.bshape.meal;

import com.rest.bshape.Product.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Setter
@Getter
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String mealName;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Product> products;
}
