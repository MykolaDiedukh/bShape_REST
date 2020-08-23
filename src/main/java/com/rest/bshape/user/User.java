package com.rest.bshape.user;

import com.rest.bshape.entity.BodyType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
 class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(min = 3)
    private String firstName;

    @NotEmpty
    @Size(min = 2)
    private String lastName;

    @NotNull
    private Integer age;

    @NotNull
    private Double weight;

    @NotNull
    private Double height;

    @NotNull
    private Integer sex;

    @NotNull
    @Size(min = 6)
    private String password;

    @Email
    @NotNull
    @Size(min = 5, max = 30)
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    private BodyType bodyType;

}

