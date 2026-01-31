package com.snapppay.expensetracker.infrastructure.persistence.entity.tag;

import com.github.f4b6a3.ulid.UlidCreator;
import com.snapppay.expensetracker.infrastructure.persistence.entity.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
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
@Table(name = "tag")
public class TagEntity {

  @Id
  @Column(columnDefinition = "BINARY(16)")
  private UUID id;

  @Column(nullable = false, columnDefinition = "VARCHAR(50)")
  private String name;

  @Column(nullable = false)
  private Long userId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "userId",  insertable = false, updatable = false)
  private UserEntity user;

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
