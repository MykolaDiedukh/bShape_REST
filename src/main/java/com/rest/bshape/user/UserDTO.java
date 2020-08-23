package com.rest.bshape.user;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public
class UserDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private Integer age;

    private Double weight;

    private Double height;

    private Integer sex;

    private String password;

    private String email;

//    private BodyTypeDTO bodyType;//powinno byc DTO
}
