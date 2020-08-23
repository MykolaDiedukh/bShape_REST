package com.rest.bshape.meal;

import com.rest.bshape.product.Product;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String mealName;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Product> products;
}
