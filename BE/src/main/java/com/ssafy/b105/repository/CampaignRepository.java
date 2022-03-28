package com.ssafy.b105.repository;

import com.ssafy.b105.dto.CampaignListDto;
import com.ssafy.b105.entity.Campaign;
import com.ssafy.b105.entity.CampaignType;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CampaignRepository extends JpaRepository<Campaign, Long>,CampaignSearchRepository {

    Optional<Campaign> findById(Long aLong);
    Page<CampaignListDto> findByType(CampaignType type, Pageable pageable);
}
