package com.ssafy.b105.repository;

import com.ssafy.b105.dto.UserDonateDto;
import com.ssafy.b105.entity.user.User;
import java.util.List;

public interface UserSuppottLogRepository {
    List<UserDonateDto> userDonate(User user);
}
