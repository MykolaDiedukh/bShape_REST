package com.rest.bshape.controller;

import com.rest.bshape.entity.Users;
import com.rest.bshape.exeption.ResourceNotFoundException;
import com.rest.bshape.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<Users> findAll(){
        return this.userRepository.findAll();
    }

    @GetMapping("/{id}")
    public Users findById(@PathVariable(value = "id") Long id){
        return this.userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + id));
    }

    @PostMapping
    Users create(@RequestBody Users users){
        return  this.userRepository.save(users);
    }

    @PutMapping("/{id}")
    public Users update(@RequestBody Users users, @PathVariable("id") Long id){
        Users existingUsers = this.userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id:"+ id));
        existingUsers.setFirstName(users.getFirstName());
        existingUsers.setLastName(users.getLastName());
        existingUsers.setAge(users.getAge());
        existingUsers.setWeight(users.getWeight());
        existingUsers.setHeight(users.getHeight());
        existingUsers.setSex(users.getSex());
        existingUsers.setPassword(users.getPassword());
        existingUsers.setEmail(users.getEmail());
        return this.userRepository.save(existingUsers);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Users> delete(@PathVariable("id") Long id){
        Users existingUsers = this.userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id:"+ id));
        this.userRepository.delete(existingUsers);
        return ResponseEntity.ok().build();
    }
}
