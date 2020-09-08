package com.rest.bshape.config;

import com.rest.bshape.user.domain.Role;
import com.rest.bshape.user.domain.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
public class AppConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    // interfejs funkcjyjny do pisania lambdy, po uruchomieniu się springa odpalany
    @Bean
    public CommandLineRunner commandLineRunner(RoleRepository roleRepository) {
        return args -> {
            Optional<Role> roleUser = roleRepository.findByName("ROLE_USER");
            if (roleUser.isEmpty()) {
                roleRepository.save(new Role(null, "ROLE_USER"));
            }
        };
    }

    // dorobić Admina lub inne role
}
