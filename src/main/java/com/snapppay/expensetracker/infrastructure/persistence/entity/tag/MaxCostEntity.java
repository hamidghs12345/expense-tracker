package com.snapppay.expensetracker.infrastructure.persistence.entity.tag;

import com.github.f4b6a3.ulid.UlidCreator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "max_cost")
public class MaxCostEntity {

  @Id
  @Column(columnDefinition = "BINARY(16)")
  private UUID id;

  @Enumerated(EnumType.STRING)
  @Column(columnDefinition = "CHAR(20)")
  private Period period;

  @Column(columnDefinition = "DECIMAL(23, 5)")
  private BigDecimal amount;

  @Column(nullable = false)
  private UUID tagId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "tagId",  insertable = false, updatable = false)
  private TagEntity tag;

  public enum Period {
    DAILY,
    WEEKLY,
    MONTHLY,
    YEARLY
  }

  @PrePersist
  public void prePersist() {
    if (id == null) {
      id = UlidCreator.getMonotonicUlid().toUuid();
    }
  }


}
