package com.rest.bshape.sevices;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface GenericService<T> {


//    @GetMapping
    public Optional<List<T>> findAll();

//    @GetMapping("/{id}")
    public Optional<T> findById(Long id);

//    @PostMapping
    public Optional<T> create( T t);

//    @PutMapping("/{id}")
    public Optional<T> update(T t, Long id);

//    @DeleteMapping("/{id}")
    public ResponseEntity<T> delete(Long id);
}
