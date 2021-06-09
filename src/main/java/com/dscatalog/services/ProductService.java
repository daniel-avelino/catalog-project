package com.dscatalog.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.dscatalog.dto.ProductDTO;
import com.dscatalog.entities.Product;
import com.dscatalog.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	public List<ProductDTO> findAll() {
		List<ProductDTO> dto = repository.findAll().stream()
				.map(x -> new ProductDTO(x.getId(), x.getName(), x.getDescription(), x.getPrice(), x.getImgUrl()))
				.collect(Collectors.toList());
		return dto;
	}

	public List<ProductDTO> findAllByPriceDesc() {
		List<ProductDTO> dto = repository.findAll(Sort.by("price").descending()).stream()
				.map(x -> new ProductDTO(x.getId(), x.getName(), x.getDescription(), x.getPrice(), x.getImgUrl()))
				.collect(Collectors.toList());
		return dto;
	}


	public List<ProductDTO> findAllByPriceAsc() {
		List<ProductDTO> dto = repository.findAll(Sort.by("price").ascending()).stream()
				.map(x -> new ProductDTO(x.getId(), x.getName(), x.getDescription(), x.getPrice(), x.getImgUrl()))
				.collect(Collectors.toList());
		return dto;
	}
	
	public ProductDTO findByName(String name) throws Exception {
		try {
			Product xpto = repository.findByName(name);
			ProductDTO dto = new ProductDTO(xpto.getId(), xpto.getName(), xpto.getDescription(), xpto.getPrice(),
					xpto.getImgUrl());
			return dto;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public void insertProduct(ProductDTO xpto) {
		Product product = new Product(null, xpto.getName(), xpto.getDescription(), xpto.getPrice(),
				xpto.getImgUrl());
		repository.save(product);
	}

	public void deleteProductById(long id) throws Exception {
		try {
			repository.deleteById(id);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
