package com.snapppay.expensetracker.infrastructure.persistence.entity.tag;

import com.github.f4b6a3.ulid.UlidCreator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "expense")
public class ExpenseEntity {

  @Id
  @Column(columnDefinition = "BINARY(16)")
  private UUID id;
  
  @Column(columnDefinition = "DECIMAL(23,5)")
  private BigDecimal amount;
  
  @Column(nullable = false)
  private UUID tagId;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "tagId",  insertable = false, updatable = false)
  private TagEntity tag;
  
  @CreationTimestamp
  @Column(columnDefinition = "DATETIME(3)")
  private ZonedDateTime createAt;

  @UpdateTimestamp
  @Column(columnDefinition = "DATETIME(3)")
  private ZonedDateTime updateAt;

  @PrePersist
  public void prePersist() {
    if (id == null) {
      id = UlidCreator.getMonotonicUlid().toUuid();
    }
  }

}
