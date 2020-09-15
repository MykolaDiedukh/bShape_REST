package com.rest.bshape.user.domain;

import com.rest.bshape.bodytype.domain.BodyType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(min = 3)
    private String firstName;

    @NotEmpty
    @Size(min = 2)
    private String lastName;

    private Integer age;

    private Double weight;

    private Double height;

    @NotNull
    private Integer sex;

    @NotNull
    private String password;

    @Email
    @NotNull

    @Size(min = 5, max = 30)
    private String email;

//    @Transient // ignoring this field when adding new 'User' to database. Probably causing mistake in future.
    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private BodyType bodyType;

    @ManyToMany/*(fetch = FetchType.EAGER)*/
    private Set<Role> roles;

}

