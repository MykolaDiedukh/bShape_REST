package com.rest.bshape.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Setter
@Getter
public class BodyType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3)
    private String typeOfBody;

    @OneToMany(fetch = FetchType.LAZY)
    private List<User> users;
    @OneToMany(fetch = FetchType.LAZY)
    private List<UserHistory> userHistories;
}