package com.snapppay.expensetracker.infrastructure.service;

import com.snapppay.expensetracker.domain.error.Error.UnauthorizedException;
import com.snapppay.expensetracker.infrastructure.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByMobile(username)
        .orElseThrow(UnauthorizedException::new);
  }

}
