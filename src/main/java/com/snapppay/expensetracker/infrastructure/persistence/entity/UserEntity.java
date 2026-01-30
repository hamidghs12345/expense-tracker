package com.snapppay.expensetracker.infrastructure.persistence.entity;

import com.github.f4b6a3.ulid.UlidCreator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, columnDefinition = "BINARY(16)")
    private UUID userId;

    @CreationTimestamp
    @Column(columnDefinition = "DATETIME(3)")
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    @Column(columnDefinition = "DATETIME(3)")
    private ZonedDateTime updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    private UserRoleEntity role;

    @Column(unique = true, length = 20)
    private String mobile;


    @Column(nullable = false)
    private String password;
    private String fingerprint;

    @PrePersist
    public void prePersist() {
        if (userId == null) {
            userId = UlidCreator.getUlid().toUuid();
        }
    }
}
