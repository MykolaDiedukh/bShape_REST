package com.rest.bshape.sevices;

import com.rest.bshape.UserDTO;
import com.rest.bshape.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface MainService<T> {


//    @GetMapping
    public List<T> findAll();

//    @GetMapping("/{id}")
    public Optional<UserDTO> findById(Long id);

//    @PostMapping
    public T create( T t);

//    @PutMapping("/{id}")
    public T update(T t, Long id);

//    @DeleteMapping("/{id}")
    public ResponseEntity<T> delete(Long id);
}
