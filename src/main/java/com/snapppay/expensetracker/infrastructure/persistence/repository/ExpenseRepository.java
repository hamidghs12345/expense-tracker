package com.snapppay.expensetracker.infrastructure.persistence.repository;

import com.snapppay.expensetracker.infrastructure.persistence.entity.tag.ExpenseEntity;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<ExpenseEntity, UUID> {

  @Query("SELECT i FROM ExpenseEntity i " +
      "WHERE (i.tagId = :tagId) " +
      "AND (:fromDate IS NULL OR i.createdAt >= :fromDate) " +
      "AND (:toDate IS NULL OR i.createdAt <= :toDate)")
  Page<ExpenseEntity> getAll(
      @Param("tagId") UUID tagId,
      @Param("fromDate") ZonedDateTime fromDate,
      @Param("toDate") ZonedDateTime toDate,
      Pageable pageable
  );

  @Query("SELECT COALESCE(SUM(e.amount), 0) " +
      "FROM ExpenseEntity e " +
      "WHERE e.tagId = :tagId AND e.createdAt >= :from")
  BigDecimal sumAmountByTagIdAndCreatedAtAfter(
      @Param("tagId") UUID tagId,
      @Param("from") ZonedDateTime from
  );
}
