package com.ssafy.b105.repository;

import com.ssafy.b105.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> , UserSupportLogRepository {

  //사용중인 닉네임인지 확인
  Optional<User> findOneByName(String username);

  Optional<User> findByName(String username);

  Optional<User> findByPrincipal(String username);

  Optional<Object> findOneByPrincipal(String principal);

  Optional<User> findByPrincipalAndVendor(String email, String registrationId);
}
