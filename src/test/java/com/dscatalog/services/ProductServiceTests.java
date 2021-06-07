package com.dscatalog.services;

import static org.mockito.Mockito.times;

import java.time.Instant;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.dscatalog.entities.Client;
import com.dscatalog.repositories.ClientRepository;

import javassist.NotFoundException;

@ExtendWith(SpringExtension.class)
public class ProductServiceTests {

	@InjectMocks
	private ClientService service;

	@Mock
	private ClientRepository repository;

	private long existingId;
	private long nonExistingId;

	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 2000L;
		Client client = new Client(1L, "Daniel", "123123.123-12", 1700.00, Instant.now(), 1);
		repository.save(client);
	}

	@Test
	public void deleteShouldDoNothingWhenIdExists() {

		Assertions.assertDoesNotThrow(() -> {
			service.deleteClient(nonExistingId);
		});

		Mockito.verify(repository, times(1)).deleteById(existingId);
	}
}
