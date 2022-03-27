package com.ssafy.b105.service;

import com.ssafy.b105.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {
  @Override
  UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
  User saveOrUpdateUser(User user);
  boolean duplicatePrincipalCheck(String principal);
  boolean duplicateNameCheck(String name);
}
