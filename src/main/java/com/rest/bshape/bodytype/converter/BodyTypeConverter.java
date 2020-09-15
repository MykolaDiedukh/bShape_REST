package com.rest.bshape.bodytype.converter;

import com.rest.bshape.bodytype.domain.BodyType;
import com.rest.bshape.bodytype.domain.BodyTypeDTO;

import java.util.List;
import java.util.stream.Collectors;


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

    // zmapowalem BodyTYpe na liste BodyTYpeDto zeby latwiej wykorzystywac solid, Single resposibility converter do mapowania, controler to fasada z opakowującymi metodami
    public static List<BodyTypeDTO> mapToListDto(List<BodyType> bodyTypeList){
        return bodyTypeList.stream()
                .map(BodyTypeConverter::convertToDTO)
                .collect(Collectors.toList());
    }


}
