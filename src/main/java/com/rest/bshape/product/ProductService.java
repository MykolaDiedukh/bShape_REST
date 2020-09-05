package com.rest.bshape.product;

import com.rest.bshape.exception.ResourceNotFoundException;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> findAll() {
        List<Product> optionalProduct = this.productRepository.findAll();
        return optionalProduct.isEmpty() ? Collections.emptyList() : optionalProduct.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ProductDTO> findById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.isEmpty() ? Optional.empty() : optionalProduct.map(this::convertToDTO);
    }

    public Optional<ProductID> create(ProductDTO productDTO) {
        Product product = this.convertFromDTO(productDTO);

        Product createdProduct = productRepository.save(product);
        val productID = new ProductID(createdProduct.getId());
        return Optional.of(productID);
    }

    public Optional<ProductDTO> update(ProductDTO productDTO, Long id) {
        Product product = this.convertFromDTO(productDTO);

        Optional<Product> productById = productRepository.findById(id);
        if (productById.isEmpty()) {
            return Optional.empty();
        }
        Product existingProduct = productById.get();
        existingProduct.setName(product.getName());
        existingProduct.setAlcohol(product.getAlcohol());
        existingProduct.setCalories(product.getCalories());
        existingProduct.setCarbohydrates(product.getCarbohydrates());
        existingProduct.setFat(product.getFat());
        existingProduct.setGigajoule(product.getGigajoule());
        existingProduct.setProtein(product.getProtein());
        return Optional.of(this.convertToDTO(productRepository.save(existingProduct)));
    }


    public ResponseEntity<ProductID> delete(Long id) {
        Product existingProduct = this.productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id:" + id));
        this.productRepository.delete(existingProduct);
        return ResponseEntity.ok().build();
    }

    private ProductDTO convertToDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .weight(product.getWeight())
                .alcohol(product.getAlcohol())
                .calories(product.getCalories())
                .carbohydrates(product.getCarbohydrates())
                .fat(product.getFat())
                .gigajoule(product.getGigajoule())
                .protein(product.getProtein())
                .build();
    }

    private Product convertFromDTO(ProductDTO productDTO) {
        return Product.builder()
                .id(productDTO.getId())
                .name(productDTO.getName())
                .weight(productDTO.getWeight())
                .alcohol(productDTO.getAlcohol())
                .calories(productDTO.getCalories())
                .carbohydrates(productDTO.getCarbohydrates())
                .fat(productDTO.getFat())
                .gigajoule(productDTO.getGigajoule())
                .protein(productDTO.getProtein())
                .build();
    }
}
