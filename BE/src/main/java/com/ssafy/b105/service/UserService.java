package com.ssafy.b105.service;

import com.ssafy.b105.dto.UserDonateDto;
import com.ssafy.b105.entity.common.MemberType;
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
  boolean duplicatePrincipalCheck(String principal);
  boolean duplicateNameCheck(String name);

  User saveOrUpdateUser(User user, MemberType type) throws ChangeSetPersister.NotFoundException;

  @Transactional
  User login(String prinripal, String credential) throws ChangeSetPersister.NotFoundException;

  Authority getById(long id) throws ChangeSetPersister.NotFoundException;


}
