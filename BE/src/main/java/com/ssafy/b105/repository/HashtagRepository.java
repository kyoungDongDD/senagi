package com.ssafy.b105.repository;

import com.ssafy.b105.entity.Hashtag;
import com.ssafy.b105.entity.QCampaign;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {

    Optional<Hashtag> findByName(String tagName);
    Optional<Hashtag> findByCampaigns(String tagName);

}
