package com.dscatalog.resources;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dscatalog.entities.Client;
import com.dscatalog.services.ClientService;

import javassist.NotFoundException;

@RestController
@RequestMapping(path = "/client")
public class ClientController {

	@Autowired
	private ClientService service;

	@GetMapping
	private ResponseEntity<List<Client>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}

	@GetMapping(path = "/{id}")
	private ResponseEntity<Object> findById(@PathVariable long id) throws NotFoundException {
		try {
			Client client = service.findById(id);
			return ResponseEntity.ok().body(client);
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client Id not found:" + id + " " + e.getMessage());
		}
	}

	@Transactional
	@PostMapping
	private ResponseEntity<Client> insertClient(@RequestBody Client client, @RequestParam("x") int x) throws Exception {
		service.insertClient(client);
		return new ResponseEntity<Client>(HttpStatus.CREATED);
	}

	@PutMapping(path = "/{id}")
	private ResponseEntity<Object> updateClient(@PathVariable long id, @RequestBody Client client) {
		try {
			service.updateClient(id, client);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Updated with success!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Problem with the update:" + id + " " + e.getMessage());
		}
	}

	@DeleteMapping(path = "/{id}")
	private ResponseEntity<Object> deleteById(@PathVariable long id) throws NotFoundException {
		service.deleteClient(id);
		return ResponseEntity.status(HttpStatus.OK).body("Client deleted!");
	}
}
