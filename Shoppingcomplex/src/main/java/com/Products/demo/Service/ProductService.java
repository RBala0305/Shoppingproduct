package com.Products.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Products.demo.Entity.Product;
import com.Products.demo.Exception.ProductNotFoundException;
import com.Products.demo.Repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProducts(String name, String category, Double minPrice, Double maxPrice) {
    	 try {
             if (name != null) {
                 return productRepository.findByNameContaining(name);
             } else if (category != null) {
                 return productRepository.findByCategory(category);
             } else if (minPrice != null && maxPrice != null) {
                 return productRepository.findByPriceBetween(minPrice, maxPrice);
             } else {
                 return productRepository.findAll();
             }
         } catch (Exception e) {
             throw new RuntimeException("Failed to fetch products");
         }
     }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found"));
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product productDetails) {
        Product product = getProductById(id);
        product.setName(productDetails.getName());
        product.setCategory(productDetails.getCategory());
        product.setPrice(productDetails.getPrice());
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
    	  Product product = productRepository.findById(id)
                  .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found"));
          productRepository.delete(product);
      }

    public Double getTotalPriceByCategory(String category) {
    	 List<Product> products = productRepository.findByCategory(category);
         if (products.isEmpty()) {
             throw new ProductNotFoundException("No products found in category: " + category);
         }
         return products.stream().mapToDouble(Product::getPrice).sum();
     }
}
