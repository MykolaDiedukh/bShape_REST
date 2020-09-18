package com.rest.bshape.bodytype.impl;

import java.util.List;

import com.rest.bshape.bodytype.BodyTypeRepository;
import com.rest.bshape.bodytype.domain.BodyType;
import com.rest.bshape.bodytype.domain.BodyTypeID;
import com.rest.bshape.bodytype.impl.BodyTypeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

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

    @Test
    void shouldUpdateBodyType() {
        BodyType bodyType = new BodyType();
        given(bodyTypeRepository.save(any())).willReturn(bodyType);
        given(bodyTypeRepository.findById(1L)).willReturn(Optional.of(bodyType));

        BodyType result = bodyTypeService.update(bodyType, 1L);
        assertThat(result).isEqualTo(bodyType);

    }


    @Test
    void shouldReturnValueForFindById() {
        BodyType bodyType = new BodyType();
        given(bodyTypeRepository.findById(1L)).willReturn(Optional.of(bodyType));

        BodyType result = bodyTypeService.findById(1L);
        assertThat(result).isEqualTo(bodyType);

    }

    // sprawdza czy lista przyhmuje chociaz 1 obiekt

    @Test
    void shouldFindAllBodyTypes() {
        BodyType bodyType = new BodyType();
        given(bodyTypeRepository.findAll()).willReturn(Collections.singletonList(bodyType));

        List<BodyType> result = bodyTypeService.findAll();
        assertThat(result).hasSize(1).contains(bodyType);
    }

    // sprawdza czy lista jest pusta
    @Test
    void shouldFindEmptyBodyTypes() {
        given(bodyTypeRepository.findAll()).willReturn(Collections.emptyList());

        List<BodyType> result = bodyTypeService.findAll();
        assertThat(result).isEmpty();

    }

    @Test
    void shouldCreateBodyType() {
        BodyType bodyType = new BodyType();
        bodyType.setId(1L);
        given(bodyTypeRepository.save(any())).willReturn(bodyType);

        BodyTypeID result = bodyTypeService.create(bodyType);
        assertThat(result).isEqualTo(new BodyTypeID(1L));
    }


    @Test
    void shouldDeleteBodType() {

        doNothing().when(bodyTypeRepository).deleteById(any());
        bodyTypeService.delete(1L);
        verify(bodyTypeRepository, times(1)).deleteById(1L);

    }

    @Test
    void shouldThrowExceptionDuringDeleteById(){

        doThrow(EmptyResultDataAccessException.class).when(bodyTypeRepository).deleteById(any());
        assertThatThrownBy(() -> bodyTypeService.delete(any())).isInstanceOf(EmptyResultDataAccessException.class);


    }

}
