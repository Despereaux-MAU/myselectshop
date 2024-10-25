package com.despereaux.myselectshop.service;

import com.despereaux.myselectshop.dto.ProductRequestDto;
import com.despereaux.myselectshop.dto.ProductResponseDto;
import com.despereaux.myselectshop.entity.Product;
import com.despereaux.myselectshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponseDto createProduct(ProductRequestDto requestDto) {
        Product product = productRepository.save(new Product(requestDto));
        return new ProductResponseDto(product);
    }
}
