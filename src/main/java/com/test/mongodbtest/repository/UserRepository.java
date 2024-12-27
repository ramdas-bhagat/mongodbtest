package com.test.mongodbtest.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.mongodbtest.model.User;

public interface UserRepository extends MongoRepository<User, String> {
    // Custom query methods can be added here if needed
    User findByName(String name);
}