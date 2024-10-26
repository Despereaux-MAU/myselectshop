package com.despereaux.myselectshop.repository;

import com.despereaux.myselectshop.dto.ProductResponseDto;
import com.despereaux.myselectshop.entity.Product;
import com.despereaux.myselectshop.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByUser(User user, Pageable pageable);

    Page<Product> findAllByUserAndProductFolderList_FolderId(User user, Long folderId, Pageable pageable);
}
