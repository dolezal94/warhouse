package com.sda.finalproject.authmvc;

import com.sda.finalproject.authmvc.entities.RoleEntity;
import com.sda.finalproject.authmvc.repositories.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

public class AuthMvcApplication {

    private final RoleRepository roleRepository;

    public AuthMvcApplication(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(AuthMvcApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            try {
                this.roleRepository.saveAll(List.of(
                        new RoleEntity("USER"),
                        new RoleEntity("ADMIN")
                ));
            } catch (Exception e) {
                // ...

            }
        };
    }
}
