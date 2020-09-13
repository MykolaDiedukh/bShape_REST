package com.rest.bshape.bodytype.converter;

import com.rest.bshape.bodytype.BodyType;
import com.rest.bshape.bodytype.BodyTypeDTO;


public class BodyTypeConverter {


    public static BodyTypeDTO convertToDTO(BodyType bodyType) {
        return BodyTypeDTO.builder()
                .id(bodyType.getId())
                .typeOfBody(bodyType.getTypeOfBody())
                .build();
    }

    public static BodyType convertFromDTO(BodyTypeDTO bodyTypeDTO) {
        return BodyType.builder()
                .id(bodyTypeDTO.getId())
                .typeOfBody(bodyTypeDTO.getTypeOfBody())
                .build();
    }


}
