package com.snapppay.expensetracker.infrastructure.repository;

import com.snapppay.expensetracker.infrastructure.entity.UserEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

  Optional<UserEntity> findByMobile(String mobile);

  Optional<UserEntity> findByUserId(UUID userId);

}
