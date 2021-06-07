package com.dscatalog.repositories;

import java.time.Instant;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import com.dscatalog.entities.Client;

@DataJpaTest
public class ClientRepositoryTests {

	@Autowired
	private ClientRepository repository;

	private long existingId;
	private long nonExistingId;

	@BeforeEach
	void setUp() {
		Client client = new Client(null, "Daniel", "123123.123-12", 1700.00, Instant.now(), 1);
		repository.save(client);
		existingId = 1L;
		nonExistingId = 1000L;
	}

	@Test
	public void deleteShouldDeleteObjectWhenIdExists() {
		repository.deleteById(existingId);
		Optional<Client> result = repository.findById(existingId);
		Assertions.assertFalse(result.isPresent());
	}

	@Test
	public void deleteShouldThrowExceptionDeleteObjectWhenIdNonExists() {
		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			repository.deleteById(nonExistingId);
		});
	}

	@Test
	public void saveShouldSaveObjectWhenAllItsOk() {
		Client client = new Client(null, "Test", "123123123", 500.00, Instant.now(), 1);
		repository.save(client);
		Optional<Client> savedClient = repository.findById(client.getId());
		Assertions.assertTrue(savedClient.isPresent());
	}

}
