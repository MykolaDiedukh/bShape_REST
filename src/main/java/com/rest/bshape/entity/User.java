package com.rest.bshape.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Setter
@Getter
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

    /*@NotEmpty*/
    private Integer age;

    /*@NotEmpty*/
    private Double weight;

    /*@NotEmpty*/
    /*@Size(min = 2)*/
    private Double height;

    /*@NotEmpty*/
    private Integer sex;

   /* @NotEmpty*/
    @Size(min = 6, max = 20)
    private String password;

    /*@Email
    @NotEmpty
    @Size(min = 5, max = 30)*/
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    private BodyType bodyType;



}

