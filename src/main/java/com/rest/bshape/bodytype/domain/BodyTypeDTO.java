package com.rest.bshape.bodytype.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BodyTypeDTO {

    private Long id;

    private String typeOfBody;

}
