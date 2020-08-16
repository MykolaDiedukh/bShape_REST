package com.rest.bshape.sevices;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface MainService<T> {


//    @GetMapping
    public List<T> findAll();

//    @GetMapping("/{id}")
    public T findById( Long id);

//    @PostMapping
    public T create( T t);

//    @PutMapping("/{id}")
    public T update(T t, Long id);

//    @DeleteMapping("/{id}")
    public ResponseEntity<T> delete(Long id);
}
