package org.yahid.grandLine.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yahid.grandLine.model.ProductInfo;
import org.yahid.grandLine.repository.ProductRepository;

import java.util.List;

@RestController
@RequestMapping("/customer/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<ProductInfo> getProducts(){
        return productRepository.findAllByOrderByCreatedAtDesc();
    }

}
