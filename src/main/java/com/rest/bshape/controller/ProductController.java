package com.rest.bshape.controller;

import com.rest.bshape.entity.Product;
import com.rest.bshape.exeption.ResourceNotFoundException;
import com.rest.bshape.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }


    @GetMapping("/{id}")
    public Product findById(@PathVariable(value = "id") Long id) {
        return this.productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id :" + id));
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        return this.productRepository.save(product);
    }


    @PutMapping("/{id}")
    public Product update(@RequestBody Product product, @PathVariable("id") Long id) {
        Product existingProduct = this.productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id:" + id));
        existingProduct.setName(product.getName());
        existingProduct.setGigajoule(product.getGigajoule());
        existingProduct.setCalories(product.getCalories());
        existingProduct.setAlcohol(product.getAlcohol());
        existingProduct.setProtein(product.getProtein());
        existingProduct.setFat(product.getFat());
        existingProduct.setCarbohydrates(product.getCarbohydrates());
        return this.productRepository.save(existingProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathVariable("id") Long id) {
        Product existingProduct = this.productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id:" + id));
        this.productRepository.delete(existingProduct);
        return ResponseEntity.ok().build();
    }
}
