package com.test.mongodbtest.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.test.mongodbtest.model.User;
import com.test.mongodbtest.repository.UserRepository;

@Configuration
public class LoadInitialUsers {
    
    @Bean
    public CommandLineRunner loadDummyUsers(UserRepository userRepository) {
        return (args) -> {
            User user1 = new User("1", "User1", "user1@testmail.com");
            User user2 = new User("2", "User2", "user2@testmail.com");
            User user3 = new User("3", "User3", "user3@testmail.com");
            User user4 = new User("4", "User4", "user4@testmail.com");

            List<User> users = List.of(user1, user2, user3, user4);

            userRepository.saveAll(users);
        };
    }
}
