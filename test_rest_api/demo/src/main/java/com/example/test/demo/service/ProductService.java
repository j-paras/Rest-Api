package com.example.test.demo.service;

import com.example.test.demo.entity.Product;
import com.example.test.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public Product saveProduct(Product product){
        try{
            return productRepository.save(product);
        }catch (Exception e){
            throw new RuntimeException("Failed to save products:" + e.getMessage());
        }
    }

    public List<Product> fetchAllProducts() {
        try {
            return productRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch all products:" + e.getMessage());
        }
    }

    public Optional<Product> fetchProductById(Long id){
        try{
            return productRepository.findById(id);
        }catch (Exception e){
            throw new RuntimeException("Failed to fetch product by ID:" + e.getMessage());
        }
    }

    public Optional<Product> updateProduct(Long id, Product updatedProduct){
        try{
            Optional<Product> existingProductOptional=productRepository.findById(id);
            if(existingProductOptional.isPresent()){
                Product existingProduct=existingProductOptional.get();
                existingProduct.setName(existingProduct.getName());
                existingProduct.setQuantity(updatedProduct.getQuantity());
                existingProduct.setPrice(updatedProduct.getQuantity());
                Product savedEntity=productRepository.save(existingProduct);
                return Optional.of(savedEntity);
            }
            else{
                return Optional.empty();
            }
        }catch (Exception e){
            throw new RuntimeException("Failed to update the product:"+ e.getMessage());
        }
    }
    //doubt
    public boolean deleteById(Long id){
        try{
            productRepository.deleteById(id);
            return true;
        }catch (Exception e){
            throw new RuntimeException("Failed to delete the product:" + e.getMessage());
        }
    }
}