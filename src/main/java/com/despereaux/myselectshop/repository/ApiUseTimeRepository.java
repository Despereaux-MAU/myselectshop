package com.despereaux.myselectshop.repository;

import com.despereaux.myselectshop.entity.ApiUseTime;
import com.despereaux.myselectshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApiUseTimeRepository extends JpaRepository<ApiUseTime, Long> {
    Optional<ApiUseTime> findByUser(User user);
}
