package com.dscatalog.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dscatalog.dto.UserDTO;
import com.dscatalog.entities.User;
import com.dscatalog.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	public void insertClient(UserDTO dto) {
		User user = new User(null, dto.getFirstName(), dto.getLastName(), dto.getEmail(),
				encoder.encode(dto.getPassword()));
		repository.save(user);
	}

	public void deleteClientById(long id) throws Exception {
		try {
			repository.deleteById(id);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public List<UserDTO> findAll() {
		List<UserDTO> dto = repository.findAll().stream()
				.map(x -> new UserDTO(x.getId(), x.getFirstName(), x.getLastName(), x.getEmail(), x.getPassword()))
				.collect(Collectors.toList());
		return dto;
	}

	public UserDTO findById(long id) {
		User user = repository.findById(id).get();
		UserDTO dto = new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(),
				user.getPassword());
		return dto;
	}
}
