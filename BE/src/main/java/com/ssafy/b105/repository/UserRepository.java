package com.ssafy.b105.repository;

import com.ssafy.b105.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

  //authority 갯수만큼 select 쿼리를 날라갈것(n+1 문제 발생)을
  //@EntityGraph로 authority를 함께 패치해 오도록 해서 1번의 조인 쿼리만 실행됨

  //동명 이인이 있을수 있으므로 아이디로 중복 검증
  @EntityGraph(attributePaths = "userAuthorities")
  Optional<User> findOneWithUserAuthoritiesByPrincipal(String principal);

  //사용중인 닉네임인지 확인
  Optional<User> findOneByName(String username);

}
