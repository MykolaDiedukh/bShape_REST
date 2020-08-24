package com.rest.bshape.bodytype;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class BodyTypeDTO {

    private Long id;

    private String typeOfBody;

}
