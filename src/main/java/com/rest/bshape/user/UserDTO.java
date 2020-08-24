package com.rest.bshape.user;

import com.rest.bshape.bodytype.BodyTypeDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
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

    private BodyTypeDTO bodyTypeDTO;
}
