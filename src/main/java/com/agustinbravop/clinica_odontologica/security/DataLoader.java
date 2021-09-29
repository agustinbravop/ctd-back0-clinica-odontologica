package com.agustinbravop.clinica_odontologica.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private final UserRepository userRepository;

    @Autowired
    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPass = encoder.encode("password");

        userRepository.deleteAll();

        userRepository.save(new User(
                "Agustín Bravo",
                "agustinbravop",
                "agus@dh.com",
                hashedPass,
                UserRole.ADMIN)
        );
        userRepository.save(new User(
                "Ariana Bittel",
                "ariana_bittel",
                "ari@dh.com",
                hashedPass,
                UserRole.USER)
        );
    }
}
