package com.rest.bshape.sevices;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface GenericService<T> {

    List<T> findAll();

    Optional<T> findById(Long id);

    Optional<T> create( T t);

    Optional<T> update(T t, Long id);

    ResponseEntity<T> delete(Long id);

}
