package com.rest.bshape.product;

import com.rest.bshape.exeption.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:4200")
class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDTO> findAll() {
        return this.productService.findAll();
    }


    @GetMapping("/{id}")
    public ProductDTO findById(@PathVariable(value = "id") Long id) {
        return productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with id :" + id));
    }

    @PostMapping
    public ProductID create(@RequestBody ProductDTO productDTO) {
        return productService.create(productDTO).orElseThrow(() -> new ResourceNotFoundException("Product not created"));
    }


    @PutMapping("/{id}")
    public ProductDTO update(@RequestBody ProductDTO productDTO, @PathVariable("id") Long id) {
        return productService.update(productDTO, id).orElseThrow(() -> new ResourceNotFoundException("Product not found with id :" + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductID> delete(@PathVariable("id") Long id) {
        return this.productService.delete(id);
    }
}
