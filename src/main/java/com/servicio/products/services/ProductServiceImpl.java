package com.servicio.products.services;

import java.util.List;
import java.util.Optional;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servicio.products.entities.Product;
import com.servicio.products.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final Environment environment;

    public ProductServiceImpl(ProductRepository productRepository, Environment environment) {
	this.productRepository = productRepository;
	this.environment = environment;

    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {

	return ((List<Product>) this.productRepository.findAll()).stream().map(product -> {

	    product.setPort(Integer.parseInt(environment.getProperty("local.server.port")));

	    return product;

	}).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {

	return this.productRepository.findById(id).map(product -> {

	    product.setPort(Integer.parseInt(environment.getProperty("local.server.port")));

	    return product;

	});
    }

}
