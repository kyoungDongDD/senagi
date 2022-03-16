package com.ssafy.b105.repository;

import com.ssafy.b105.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
