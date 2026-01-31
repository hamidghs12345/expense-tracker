package com.snapppay.expensetracker.infrastructure.persistence.repository;

import com.snapppay.expensetracker.infrastructure.persistence.entity.tag.TagEntity;
import java.time.ZonedDateTime;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, UUID> {

  @Query("SELECT i FROM TagEntity i " +
      "WHERE (i.userId = :userId) " +
      "AND (:name IS NULL OR i.name = :name) " +
      "AND (:fromDate IS NULL OR i.createdAt >= :fromDate) " +
      "AND (:toDate IS NULL OR i.createdAt <= :toDate)")
  Page<TagEntity> getAll(
      @Param("userId") Long userId,
      @Param("name") String name,
      @Param("fromDate") ZonedDateTime fromDate,
      @Param("toDate") ZonedDateTime toDate,
      Pageable pageable
  );

}
