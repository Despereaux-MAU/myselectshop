package com.despereaux.myselectshop.controller;

import com.despereaux.myselectshop.dto.ProductMypriceRequestDto;
import com.despereaux.myselectshop.dto.ProductRequestDto;
import com.despereaux.myselectshop.dto.ProductResponseDto;
import com.despereaux.myselectshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/products")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto requestDto) {
        return productService.createProduct(requestDto);
    }

    @PutMapping("/products/{id}")
    public ProductResponseDto updateProduct(@PathVariable Long id, @RequestBody ProductMypriceRequestDto requestDto) {
        return productService.updateProduct(id, requestDto);
    }
}
