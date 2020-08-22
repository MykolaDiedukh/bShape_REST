package com.rest.bshape.sevices;

import com.rest.bshape.entity.Product;
import com.rest.bshape.entity.User;
import com.rest.bshape.exeption.ResourceNotFoundException;
import com.rest.bshape.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements MainService<Product> {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return this.productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id :" + id));
    }

    @Override
    public Product create(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public Product update(Product product, Long id) {
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

    @Override
    public ResponseEntity<Product> delete(Long id) {
        Product existingProduct = this.productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id:" + id));
        this.productRepository.delete(existingProduct);
        return ResponseEntity.ok().build();
    }
}
