package com.ssafy.b105.service;

import com.ssafy.b105.dto.UserDonateDto;
import com.ssafy.b105.entity.user.Authority;
import com.ssafy.b105.entity.user.User;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public interface UserService extends UserDetailsService {
  @Override
  UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
  User saveOrUpdateUser(User user) throws ChangeSetPersister.NotFoundException;
  boolean duplicatePrincipalCheck(String principal);
  boolean duplicateNameCheck(String name);

  @Transactional
  User login(String prinripal, String credential);

  Authority getById(long id) throws ChangeSetPersister.NotFoundException;

  void setAuthority(User user, Authority authority);

}
