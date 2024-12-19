package com.servicio.products.repositories;

import org.springframework.data.repository.CrudRepository;

import com.servicio.products.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
