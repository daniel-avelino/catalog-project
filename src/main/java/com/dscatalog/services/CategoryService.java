package com.dscatalog.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dscatalog.dto.CategoryDTO;
import com.dscatalog.entities.Category;
import com.dscatalog.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;

	public void insertCategory(Category category) {
		repository.save(category);
	}

	public void deleteCategoryById(long id) {
		try {
			repository.deleteById(id);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("An error deleting was found in id: " + id + " - " + e.getMessage());
		}
	}

	public CategoryDTO findCategoryByName(String name) {
		try {
			Category category = repository.findByName(name);
			CategoryDTO dto = new CategoryDTO(category.getId(), category.getName());
			return dto;
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Category not found: " + name + " - " + e.getMessage());
		}
	}

	public CategoryDTO findCategoryById(long id) throws NoSuchElementException{
		try {
			Category category = repository.findById(id).get();
			CategoryDTO dto = new CategoryDTO(category.getId(), category.getName());
			return dto;
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException("Category not found: " + id + " - " + e.getMessage());
		}
	}

	public List<CategoryDTO> findAllCategories() {
		List<Category> list = repository.findAll();
		List<CategoryDTO> dto = list.stream().map(x -> new CategoryDTO(x.getId(), x.getName()))
				.collect(Collectors.toList());
		return dto;
	}

	public void updateCategory(long id, Category category) {
		try {
			Category old = repository.findById(id).get();
			old.setName(category.getName());
			repository.save(old);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Not updated");
		}
	}

}
