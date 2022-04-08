package com.ssafy.b105.repository;

<<<<<<< HEAD
import com.ssafy.b105.entity.Campaign;
=======
import com.ssafy.b105.entity.campaign.Campaign;

import java.util.List;
>>>>>>> dev
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CampaignRepository extends JpaRepository<Campaign, Long>,CampaignSearchRepository {

    Optional<Campaign> findById(Long aLong);
<<<<<<< HEAD
=======

  List<Campaign> findAllByUserId(Long userId);
>>>>>>> dev
}
