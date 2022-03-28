package com.ssafy.b105.repository;

import com.ssafy.b105.entity.Campaign;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CampaignRepository extends JpaRepository<Campaign, Long>,CampaignSearchRepository {

    Optional<Campaign> findById(Long aLong);
}
