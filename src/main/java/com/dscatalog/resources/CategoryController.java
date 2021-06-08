package com.dscatalog.resources;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dscatalog.entities.Category;
import com.dscatalog.services.CategoryService;

@RestController
@RequestMapping(path = "/category")
public class CategoryController {

	@Autowired
	private CategoryService services;

	@GetMapping
	private ResponseEntity<?> findAll() {
		return ResponseEntity.ok().body(services.findAllCategories());
	}

	@GetMapping(path = "/{id}")
	private ResponseEntity<?> findById(@PathVariable long id) {
		try {
			return ResponseEntity.ok().body(services.findCategoryById(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found: " + id);
		}
	}

	@PostMapping
	private ResponseEntity<?> insertCategory(@RequestBody Category category) {
		try {
			services.insertCategory(category);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Problems to insert this category.");
		}
	}

}
