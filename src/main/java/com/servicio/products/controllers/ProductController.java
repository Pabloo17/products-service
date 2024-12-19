package com.servicio.products.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.servicio.products.entities.Product;
import com.servicio.products.services.ProductService;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
	this.productService = productService;

    }

    @GetMapping
    public ResponseEntity<List<Product>> list() {
	return ResponseEntity.ok(this.productService.findAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> details(@PathVariable Long id) {
	Optional<Product> productOptional = this.productService.findById(id);

	return productOptional.isPresent() ? ResponseEntity.ok(productOptional.orElseThrow())
		: ResponseEntity.notFound().build();

    }

}
