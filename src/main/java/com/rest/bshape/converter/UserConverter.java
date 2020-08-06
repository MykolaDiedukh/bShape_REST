package com.rest.bshape.converter;

import com.rest.bshape.entity.User;
import com.rest.bshape.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import java.util.Optional;

/*public class UserConverter implements Converter<Long, User> {

    @Override
    public User convert(Long Long) {
        return null;
    }


    @Autowired
    private UserRepository userRepository;

    public Optional<User> convert(double height) {
        return this.userRepository.findById(Double.doubleToLongBits(height));
    }

}*/
