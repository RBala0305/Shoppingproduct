package com.Products.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Products.demo.Entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product>findByNameContaining(String name);
	
	List<Product> findByCategory(String category);
	
	List<Product> findByPriceBetween(Double minprice,Double maxprice);
	
}
