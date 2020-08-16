package com.rest.bshape.controller;

import com.rest.bshape.entity.Product;
import com.rest.bshape.sevices.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> findAll() {
        return this.productService.findAll();
    }


    @GetMapping("/{id}")
    public Product findById(@PathVariable(value = "id") Long id) {
        return this.productService.findById(id);
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        return this.productService.create(product);
    }


    @PutMapping("/{id}")
    public Product update(@RequestBody Product product, @PathVariable("id") Long id) {
        return this.productService.update(product, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathVariable("id") Long id) {
        return this.productService.delete(id);
    }
}
