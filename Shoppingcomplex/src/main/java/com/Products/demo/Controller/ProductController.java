package com.Products.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Products.demo.Entity.Product;
import com.Products.demo.Service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	    @Autowired
	    private ProductService productService;

	    @GetMapping
	    public ResponseEntity<List<Product>> getProducts(
	            @RequestParam(required = false) String name,
	            @RequestParam(required = false) String category,
	            @RequestParam(required = false) Double minPrice,
	            @RequestParam(required = false) Double maxPrice) {
	        List<Product> products = productService.getProducts(name, category, minPrice, maxPrice);
	        return new ResponseEntity<>(products, HttpStatus.OK);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
	        Product product = productService.getProductById(id);
	        return new ResponseEntity<>(product, HttpStatus.OK);
	    }

	    @PostMapping
	    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
	        Product createdProduct = productService.createProduct(product);
	        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
	        Product updatedProduct = productService.updateProduct(id, productDetails);
	        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
	        productService.deleteProduct(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }

	    @GetMapping("/price/category")
	    public ResponseEntity<Double> getTotalPriceByCategory(@RequestParam String category) {
	        Double totalPrice = productService.getTotalPriceByCategory(category);
	        return new ResponseEntity<>(totalPrice, HttpStatus.OK);
	    }
	}

