package com.snapppay.expensetracker.infrastructure.persistence.entity;

import com.snapppay.expensetracker.domain.model.UserAuthority;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "user_role")
public class UserRoleEntity implements GrantedAuthority {

    @Id
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(20)")
    private UserAuthority authority;

    private String title;

    public String getAuthority() {
        return authority.name();
    }

}
