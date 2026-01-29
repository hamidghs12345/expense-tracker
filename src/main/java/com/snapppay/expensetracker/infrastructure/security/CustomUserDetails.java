package com.snapppay.expensetracker.infrastructure.security;

import com.snapppay.expensetracker.domain.model.UserAuthority;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {

  private Long id;
  private String username;
  private UserAuthority authority;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    String[] userRoles = new String[] { authority.name() };

    return AuthorityUtils.createAuthorityList(userRoles);
  }

  @Override
  public String getPassword() {
    return "";
  }

  @Override
  public String getUsername() {
    return username;
  }
}

