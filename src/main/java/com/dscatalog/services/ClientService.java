package com.dscatalog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dscatalog.entities.Client;
import com.dscatalog.repositories.ClientRepository;

import javassist.NotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	public List<Client> findAll() {
		return repository.findAll();
	}

	public Client findById(Long id) throws NotFoundException {
		return repository.findById(id).orElseThrow(() -> new NotFoundException("Client Id not found: " + id));
	}

	public void insertClient(Client client) {
		repository.saveAndFlush(client);
	}

	public void updateClient(long id, Client newClient) throws NotFoundException {
		Client client = repository.findById(id).orElseThrow(() -> new NotFoundException("Client Id not found: " + id));
		client.updateClient(newClient, id);
		repository.saveAndFlush(client);

	}

	public void deleteClient(long id) throws NotFoundException {
		repository.findById(id).orElseThrow(() -> new NotFoundException("Client Id not found: " + id));
		repository.deleteById(id);
	}
}
