package com.despereaux.myselectshop.repository;

import com.despereaux.myselectshop.entity.Product;
import com.despereaux.myselectshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByUser(User user);
}
