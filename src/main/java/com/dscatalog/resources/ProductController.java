package com.dscatalog.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dscatalog.dto.ProductDTO;
import com.dscatalog.services.ProductService;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

	@Autowired
	private ProductService service;

	@GetMapping
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}

	@GetMapping(path = "/")
	public ResponseEntity<?> findByName(@RequestParam("name") String name) throws Exception {
		try {
			return ResponseEntity.ok().body(service.findByName(name));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
		}
	}

	@GetMapping(path = "/expensive")
	public ResponseEntity<?> findAllByPriceDesc() {
		return ResponseEntity.ok().body(service.findAllByPriceDesc());
	}

	@GetMapping(path = "/cheaper")
	public ResponseEntity<?> findAllByPriceAsc() {
		return ResponseEntity.ok().body(service.findAllByPriceAsc());
	}

	@PostMapping
	public ResponseEntity<?> insertProduct(@RequestBody ProductDTO product) {
		try {
			service.insertProduct(product);
			return new ResponseEntity<ProductDTO>(HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Problems with the new product!");
		}
	}
}
