package com.rest.bshape.user;

import com.rest.bshape.exeption.ResourceNotFoundException;
import com.rest.bshape.user.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable(value = "id") Long id) {
        return userService.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + id));
    }

    @PostMapping
    public UserID createUser(@RequestBody UserDTO userDTO) {
        return userService.create(userDTO).orElseThrow(() -> new ResourceNotFoundException("User not created"));
    }

    @PutMapping("/{id}")
    public UserDTO update(@RequestBody UserDTO userDTO, @PathVariable("id") Long id) {
        return userService.update(userDTO, id).orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable("id") Long id) {
        return this.userService.delete(id);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> getLogin(@RequestBody UserDTO userDTO) {
        return userService.getLogin(userDTO).isPresent() ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
