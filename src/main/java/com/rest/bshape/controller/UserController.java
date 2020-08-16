package com.rest.bshape.controller;

import com.rest.bshape.entity.User;
import com.rest.bshape.sevices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> findAll() {
        return this.userService.findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable(value = "id") Long id) {
        return this.userService.findById(id);
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return this.userService.create(user);
    }

    @PutMapping("/{id}")
    public User update(@RequestBody User user, @PathVariable("id") Long id) {
        return this.userService.update(user, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable("id") Long id) {
        return this.userService.delete(id);
    }
}
