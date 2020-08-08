package com.rest.bshape.controller;

import com.rest.bshape.entity.User;
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
    public List<User> findAll(){
        return this.userRepository.findAll();
    }


    @GetMapping("/{id}")
    public User findById(@PathVariable(value = "id") Long id){
        return this.userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + id));
    }

    @PostMapping
    public User create(@RequestBody User user){
        return  this.userRepository.save(user);
    }




    @PutMapping("/{id}")
    public User update(@RequestBody User user, @PathVariable("id") Long id){
        User existingUser = this.userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id:"+ id));
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setAge(user.getAge());
        existingUser.setWeight(user.getWeight());
        existingUser.setHeight(user.getHeight());
        existingUser.setSex(user.getSex());
        existingUser.setPassword(user.getPassword());
        existingUser.setEmail(user.getEmail());
        return this.userRepository.save(existingUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable("id") Long id){
        User existingUser = this.userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id:"+ id));
        this.userRepository.delete(existingUser);
        return ResponseEntity.ok().build();
    }
}
