package com.despereaux.myselectshop.repository;

import com.despereaux.myselectshop.entity.ProductFolder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductFolderRepository extends JpaRepository<ProductFolder, Long> {
}
