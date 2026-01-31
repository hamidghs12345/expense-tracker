package com.snapppay.expensetracker.infrastructure.persistence.repository;

import com.snapppay.expensetracker.infrastructure.persistence.entity.tag.TagEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, UUID> {
}
