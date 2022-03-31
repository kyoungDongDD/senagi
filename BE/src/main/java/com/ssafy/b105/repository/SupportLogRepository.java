package com.ssafy.b105.repository;

import com.ssafy.b105.entity.SupportLog;
import com.ssafy.b105.entity.user.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupportLogRepository extends JpaRepository<SupportLog, Long> {
}
