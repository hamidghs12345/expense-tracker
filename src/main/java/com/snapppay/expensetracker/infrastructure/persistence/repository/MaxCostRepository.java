package com.snapppay.expensetracker.infrastructure.persistence.repository;

import com.snapppay.expensetracker.infrastructure.persistence.entity.tag.MaxCostEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaxCostRepository extends JpaRepository<MaxCostEntity, UUID> {

}
