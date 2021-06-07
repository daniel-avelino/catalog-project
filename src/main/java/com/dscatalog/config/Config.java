package com.dscatalog.config;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.dscatalog.entities.Client;
import com.dscatalog.services.ClientService;

@Configuration
public class Config implements CommandLineRunner {

	@Autowired
	private ClientService service;

	@Override
	public void run(String... args) throws Exception {
		Client client = new Client(null, "Daniel", "123123.123-12", 1700.00, Instant.now(), 1);
		service.insertClient(client);
	}

}
