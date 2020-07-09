package com.rest.bshape.entity;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

    private @Id @GeneratedValue Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private Double weight;
    private Double height;
    private Integer sex;
    private String password;
    private String email;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public User setAge(Integer age) {
        this.age = age;
        return this;
    }

    public Double getWeight() {
        return weight;
    }

    public User setWeight(Double weight) {
        this.weight = weight;
        return this;
    }

    public Double getHeight() {
        return height;
    }

    public User setHeight(Double height) {
        this.height = height;
        return this;
    }

    public Integer getSex() {
        return sex;
    }

    public User setSex(Integer sex) {
        this.sex = sex;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }
}
