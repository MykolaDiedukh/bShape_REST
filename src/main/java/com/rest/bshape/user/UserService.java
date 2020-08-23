package com.rest.bshape.user;

import com.rest.bshape.exeption.ResourceNotFoundException;
import com.rest.bshape.userhistory.UserRepository;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> findAll() {
        List<User> optionalAllUser = this.userRepository.findAll();
        return optionalAllUser.isEmpty() ? Collections.emptyList() : optionalAllUser.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<UserDTO> findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.isEmpty() ? Optional.empty() : optionalUser.map(this::convertToDTO);
    }

    public Optional<UserID> create(UserDTO userDTO) {
        User user = this.convertFromDTO(userDTO);

        User createdUser = userRepository.save(user);
        val userID = new UserID(createdUser.getId());
        return Optional.of(userID);
    }

    public Optional<UserDTO> update(UserDTO userDTO, Long id) {
        User user = this.convertFromDTO(userDTO);

        Optional<User> userById = userRepository.findById(id);
        if (userById.isEmpty()) {
            return Optional.empty();
        }
        User existingUser = userById.get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setAge(user.getAge());
        existingUser.setWeight(user.getWeight());
        existingUser.setHeight(user.getHeight());
        existingUser.setSex(user.getSex());
        existingUser.setPassword(user.getPassword());
        existingUser.setEmail(user.getEmail());
        return Optional.of(this.convertToDTO(userRepository.save(existingUser)));
    }

    public ResponseEntity<User> delete(Long id) {
        User existingUser = this.userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id:" + id));
        this.userRepository.delete(existingUser);
        return ResponseEntity.ok().build();
    }

    public Optional<UserDTO> getLogin(UserDTO userDTO) {
        User user = this.convertFromDTO(userDTO);

        Optional<User> userByEmailAndPassword = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        return userByEmailAndPassword.map(this::convertToDTO);
    }

    private UserDTO convertToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .height(user.getHeight())
                .age(user.getAge())
                .password(user.getPassword())
                .sex(user.getSex())
                .weight(user.getWeight())
                .build();
    }

    private User convertFromDTO(UserDTO userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .email(userDTO.getEmail())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .height(userDTO.getHeight())
                .age(userDTO.getAge())
                .password(userDTO.getPassword())
                .sex(userDTO.getSex())
                .weight(userDTO.getWeight())
                .build();
    }
}
