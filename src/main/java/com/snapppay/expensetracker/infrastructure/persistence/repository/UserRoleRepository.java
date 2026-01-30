package com.snapppay.expensetracker.infrastructure.persistence.repository;

import com.snapppay.expensetracker.domain.model.UserAuthority;
import com.snapppay.expensetracker.infrastructure.persistence.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, UserAuthority> {

}
