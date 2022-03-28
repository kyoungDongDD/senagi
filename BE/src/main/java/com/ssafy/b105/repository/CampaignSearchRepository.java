package com.ssafy.b105.repository;

import com.ssafy.b105.dto.CampaignListDto;
import com.ssafy.b105.dto.CampaignSearchCondition;
import com.ssafy.b105.dto.HashtagDto;
import com.ssafy.b105.entity.Campaign;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CampaignSearchRepository {
    Page<CampaignListDto> searchList(CampaignSearchCondition condition, Pageable pageable);
}
