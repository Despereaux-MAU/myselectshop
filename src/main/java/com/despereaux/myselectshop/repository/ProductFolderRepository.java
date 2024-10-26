package com.despereaux.myselectshop.repository;

import com.despereaux.myselectshop.entity.Folder;
import com.despereaux.myselectshop.entity.Product;
import com.despereaux.myselectshop.entity.ProductFolder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductFolderRepository extends JpaRepository<ProductFolder, Long> {
    Optional<ProductFolder> findByProductAndFolder(Product product, Folder folder);
}
