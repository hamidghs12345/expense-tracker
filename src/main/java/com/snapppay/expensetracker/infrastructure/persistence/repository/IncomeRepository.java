package com.snapppay.expensetracker.infrastructure.persistence.repository;

import com.snapppay.expensetracker.infrastructure.persistence.entity.income.IncomeEntity;
import java.time.ZonedDateTime;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRepository extends JpaRepository<IncomeEntity, UUID> {

  @Query("SELECT i FROM IncomeEntity i " +
      "WHERE (i.userId = :userId) " +
      "AND (:source IS NULL OR i.source = :source) " +
      "AND (:fromDate IS NULL OR i.createdAt >= :fromDate) " +
      "AND (:toDate IS NULL OR i.createdAt <= :toDate)")
  Page<IncomeEntity> getAll(
      @Param("userId") Long userId,
      @Param("source") String source,
      @Param("fromDate") ZonedDateTime fromDate,
      @Param("toDate") ZonedDateTime toDate,
      Pageable pageable
  );

}
