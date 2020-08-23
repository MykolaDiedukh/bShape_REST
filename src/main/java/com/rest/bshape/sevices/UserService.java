package com.rest.bshape.sevices;

import com.rest.bshape.dto.UserDTO;
import com.rest.bshape.entity.User;
import com.rest.bshape.exeption.ResourceNotFoundException;
import com.rest.bshape.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements GenericService<UserDTO> {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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

    @Override
    public Optional<List<UserDTO>> findAll() {
        List<User> optionalAllUser = this.userRepository.findAll();
        return optionalAllUser.isEmpty() ? Optional.empty() : Optional.of(optionalAllUser.stream().map(this::convertToDTO).collect(Collectors.toList()));
    }

    @Override
    public Optional<UserDTO> findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.isEmpty() ? Optional.empty() : optionalUser.map(this::convertToDTO);
    }

    @Override
    public Optional<UserDTO> create(UserDTO userDTO) {

        S save = userRepository.save(user);
        return save;
    }

//    @Override
//    public Optional<UserDTO> update(UserDTO userDTO, Long id) {
//        return Optional.empty();
//    }

    @Override
    public Optional<UserDTO> update(UserDTO userDTO, Long id) {

        User existingUser = this.userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id:" + id));
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

    @Override
    public ResponseEntity<UserDTO> delete(Long id) {
        User existingUser = this.userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id:" + id));
        this.userRepository.delete(existingUser);
        return ResponseEntity.ok().build();
    }

    public Optional<User> getLogin(User user) {
        return userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
    }
}
