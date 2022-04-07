package com.ssafy.b105.repository;

import com.ssafy.b105.entity.campaign.Campaign;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CampaignRepository extends JpaRepository<Campaign, Long>,CampaignSearchRepository {

    Optional<Campaign> findById(Long aLong);

  List<Campaign> findAllByUserId(Long userId);
}
