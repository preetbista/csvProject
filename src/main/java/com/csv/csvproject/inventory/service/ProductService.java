package com.csv.csvproject.inventory.service;

import com.csv.csvproject.inventory.dto.ProductDeleteDto;
import com.csv.csvproject.inventory.dto.ProductRequestDto;
import com.csv.csvproject.inventory.dto.ProductResponseDto;

import java.util.List;

public interface ProductService {
    List<ProductResponseDto> getAllProducts();
    ProductResponseDto findById(int id);
    ProductResponseDto addProducts(ProductRequestDto productRequestDto);
    ProductDeleteDto deleteProduct(int id);
}
