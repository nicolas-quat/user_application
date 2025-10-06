package com.user_application;

import com.user_application.entity.Utilisateur;
import com.user_application.repository.UserRepository;
import com.user_application.utils.Gender;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {
            Utilisateur user1 = Utilisateur.builder()
                    .name("Nicolas")
                    .birthdate(new Date(1015252765000L))
                    .country("France")
                    .phone("0874581596")
                    .gender(Gender.MALE)
                    .build();
            Utilisateur user2 = Utilisateur.builder()
                    .name("Thomas")
                    .birthdate(new Date(1023201565000L))
                    .country("India")
                    .phone("0685475814")
                    .gender(Gender.OTHER)
                    .build();
            Utilisateur user3 = Utilisateur.builder()
                    .name("Elise")
                    .birthdate(new Date(928507165000L))
                    .country("France")
                    .phone("0485748541")
                    .gender(Gender.FEMALE)
                    .build();
            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);


        };
    }

}
