package com.rest.bshape.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Setter
@Getter
public class BodyType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String typeOfBody;

    @OneToMany(fetch = FetchType.LAZY)
    private List<User> users;
    @OneToMany(fetch = FetchType.LAZY)
    private List<UserHistory> userHistories;
}