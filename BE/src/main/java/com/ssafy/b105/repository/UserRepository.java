package com.ssafy.b105.repository;

<<<<<<< HEAD
import com.ssafy.b105.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
=======
import com.ssafy.b105.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> , UserSupportLogRepository {
>>>>>>> dev

  //사용중인 닉네임인지 확인
  Optional<User> findOneByName(String username);

<<<<<<< HEAD
  Optional<User> findByProviderAndProviderId(String provider, String providerId);

=======
>>>>>>> dev
  Optional<User> findByName(String username);

  Optional<User> findByPrincipal(String username);

  Optional<Object> findOneByPrincipal(String principal);
<<<<<<< HEAD
=======

  Optional<User> findByPrincipalAndVendor(String email, String registrationId);
>>>>>>> dev
}
