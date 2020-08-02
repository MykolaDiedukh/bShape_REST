package com.rest.bshape.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private Double weight;
    private Double height;
    private Integer sex;
    private String password;
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    private BodyType bodyType;

}