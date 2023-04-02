package com.csv.csvproject.inventory.service.impl;

import com.csv.csvproject.inventory.dto.ProductDeleteDto;
import com.csv.csvproject.inventory.dto.ProductRequestDto;
import com.csv.csvproject.inventory.dto.ProductResponseDto;
import com.csv.csvproject.inventory.model.Products;
import com.csv.csvproject.inventory.model.Status;
import com.csv.csvproject.inventory.repository.ProductRepository;
import com.csv.csvproject.inventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductResponseDto> getAllProducts() {
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        List<Products> productsList = productRepository.findAll();

        for (Products products : productsList) {
            ProductResponseDto productResponseDto = new ProductResponseDto();
            productResponseDto.setName(products.getName());
            productResponseDto.setQuantity(products.getQuantity());
            productResponseDto.setCode(products.getCode());
            productResponseDto.setPrice(products.getPrice());
            productResponseDtos.add(productResponseDto);
        }
        return productResponseDtos;
    }

    @Override
    public ProductResponseDto findById(int id) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        Products retrievedProduct = productRepository.findById(id).orElseThrow(null);
        if(retrievedProduct != null){
            productResponseDto.setName(retrievedProduct.getName());
            productResponseDto.setQuantity(retrievedProduct.getQuantity());
            productResponseDto.setCode(retrievedProduct.getCode());
            productResponseDto.setPrice(retrievedProduct.getPrice());
        }else{
            productResponseDto.setName(null);
            productResponseDto.setQuantity(null);
            productResponseDto.setPrice(null);
        }
        return productResponseDto;
    }
    @Override
    public ProductResponseDto addProducts(ProductRequestDto productRequestDto) {
        Products products = new Products();
        products.setName(productRequestDto.getName());
        products.setQuantity(productRequestDto.getQuantity());
        products.setCode(productRequestDto.getCode());
        products.setPrice(productRequestDto.getPrice());
        products.setStatus(Status.ACTIVE);

        Products save = productRepository.save(products);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setName(save.getName());
        productResponseDto.setQuantity(save.getQuantity());
        productResponseDto.setCode(save.getCode());
        productResponseDto.setPrice(save.getPrice());
        return productResponseDto;
    }

    @Override
    public ProductDeleteDto deleteProduct(int id) {
        Products productToBeDeleted = productRepository.findById(id).orElseThrow(null);
        productToBeDeleted.setStatus(Status.DELETED);
        productRepository.save(productToBeDeleted);
        ProductDeleteDto productDeleteDto = new ProductDeleteDto();
        productDeleteDto.setMessage("Product deleted successfully");
        return productDeleteDto;
    }

}
