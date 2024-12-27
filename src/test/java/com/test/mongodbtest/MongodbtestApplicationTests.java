package com.test.mongodbtest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.data.domain.Example;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.test.mongodbtest.model.User;
import com.test.mongodbtest.repository.UserRepository;

@SpringBootTest
@Testcontainers
class MongodbtestApplicationTests {

	@Container
	@ServiceConnection
	static MongoDBContainer container = new MongoDBContainer("mongo:8.0");

	@Autowired
	UserRepository repository;

	User dave, oliver, carter;

	@BeforeEach
	public void setUp() {

		repository.deleteAll();

		dave = repository.save(new User("1", "Carter", "Matthews"));
		oliver = repository.save(new User("2", "Oliver August", "Matthews"));
		carter = repository.save(new User("3", "Carter", "Matthews"));
	}

	@Test
	public void setsIdOnSave() {

		User dave = repository.save(new User("1", "Dave", "Matthews"));

		assertThat(dave.getId()).isNotNull();
	}

	@Test
	public void findsByLastName() {

		List<User> result = List.of(repository.findByName("Carter"));

		assertThat(result).hasSize(1).extracting("name").contains("Carter");
	}

	@Test
	public void findsByExample() {

		User probe = new User("1", "Carter", "Matthews");

		List<User> result = repository.findAll(Example.of(probe));

		assertThat(result).hasSize(1).extracting("name").contains("Carter", "Carter");
	}

}
