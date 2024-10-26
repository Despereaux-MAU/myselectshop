package com.despereaux.myselectshop.repository;

import com.despereaux.myselectshop.entity.Folder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FolderRepository extends JpaRepository<Folder, Long> {
}
