package com.rest.bshape.bodytype;

import com.rest.bshape.bodytype.domain.BodyType;
import com.rest.bshape.bodytype.impl.BodyTypeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

// Mokckito
// junit
// AssertJ

@ExtendWith(MockitoExtension.class)
class BodyTypeServiceImplTest {

    @Mock
    private BodyTypeRepository bodyTypeRepository;

    @InjectMocks
    private BodyTypeServiceImpl bodyTypeService;


    @Test
    void shouldThrowExceptionDuringUpdate() {

        //given
        given(bodyTypeRepository.findById(1L)).willReturn(Optional.empty());
        BodyType bodyType = new BodyType();
      /*  given(bodyTypeRepository.save(any())).willReturn(bodyType);*/

        Long id = 1l;
        BodyType bodyTypeParam = new BodyType(1L, "skinny");


        //when
       /* BodyType result = bodyTypeService.update(bodyTypeParam, id);*/

        //then
       /* assertThat(result).isEqualTo(BodyType.builder().typeOfBody("skinny").build()); wywali exception czyli jest git*/

        assertThatThrownBy(() -> bodyTypeService.update(bodyTypeParam, id))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("BodyType not found with id :1");

    }

}
