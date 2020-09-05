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
@EqualsAndHashCode(of="id")
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String mealName;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<Product> products;
}
