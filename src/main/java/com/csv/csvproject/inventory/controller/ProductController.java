package com.csv.csvproject.inventory.controller;

import com.csv.csvproject.inventory.dto.ProductDeleteDto;
import com.csv.csvproject.inventory.dto.ProductRequestDto;
import com.csv.csvproject.inventory.dto.ProductResponseDto;
import com.csv.csvproject.inventory.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductResponseDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductResponseDto findById(@PathVariable int id) {
        return productService.findById(id);
    }

    @PostMapping
    public ProductResponseDto addProducts(@RequestBody ProductRequestDto productRequestDto) {
        return productService.addProducts(productRequestDto);
    }

    @DeleteMapping("/{id}")
    public ProductDeleteDto deleteProduct(@PathVariable int id) {
        return productService.deleteProduct(id);
    }

}
