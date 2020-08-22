package com.rest.bshape.sevices;

import com.rest.bshape.UserDTO;
import com.rest.bshape.entity.User;
import com.rest.bshape.exeption.ResourceNotFoundException;
import com.rest.bshape.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements MainService<UserDTO> {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public Optional<UserDTO> findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()){
            return Optional.empty();
        }

        return optionalUser.map(this::convertToDTO);
    }

//    @Override
//    public User findById(Long id) {
//        return this.userRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + id));
//    }


    private UserDTO convertToDTO(User user){
        return UserDTO.builder()
                .age(user.getAge())
                //itd
                .build();
    }

    private User convertFromDTO(UserDTO userDTO){
        return User.builder()
                .age(userDTO.getAge())
                //itd
                .build();
    }


    @Override
    public UserDTO create(UserDTO user) {
        return this.userRepository.save(user);
    }

    @Override
    public User update(User user, Long id) {
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
    public ResponseEntity<User> delete(Long id) {
        User existingUser = this.userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id:" + id));
        this.userRepository.delete(existingUser);
        return ResponseEntity.ok().build();
    }

    public Optional<User> getLogin(User user) {
        return userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
    }
}
