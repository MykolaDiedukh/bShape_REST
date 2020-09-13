package com.rest.bshape.bodytype;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class BodyTypeServiceTest {

    @Mock
    private BodyTypeRepository bodyTypeRepository;

    @InjectMocks
    private BodyTypeService bodyTypeService;


    @Test
    void update() {

        //given
        given(bodyTypeRepository.findById(1L)).willReturn(Optional.empty());
        BodyType bodyType = new BodyType();
        given(bodyTypeRepository.save(any())).willReturn(bodyType);

        Long id = 1l;
        BodyTypeDTO bodyTypeDTO = new BodyTypeDTO(2l, "skinny");


        //when
        Optional<BodyTypeDTO> typeDTO = bodyTypeService.update(bodyTypeDTO, id);

        //then


    }

}
