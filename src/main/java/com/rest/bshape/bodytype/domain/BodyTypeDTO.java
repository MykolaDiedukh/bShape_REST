package com.rest.bshape.bodytype.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BodyTypeDTO {

    private Long id;

    @NotNull
    private String typeOfBody;

}
