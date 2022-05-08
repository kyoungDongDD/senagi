package com.ssafy.b105.repository;

import com.ssafy.b105.dto.CampaignListDto;
import com.ssafy.b105.dto.CampaignSearchCondition;
<<<<<<< HEAD
import com.ssafy.b105.dto.HashtagDto;
import com.ssafy.b105.entity.Campaign;
=======
import com.ssafy.b105.entity.campaign.Campaign;
>>>>>>> dev
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CampaignSearchRepository {
    Page<CampaignListDto> searchList(CampaignSearchCondition condition, Pageable pageable);
<<<<<<< HEAD
=======
    Long receiptAllAmount(Campaign campaign);
    Page<CampaignListDto> myCampaign(List<CampaignListDto> campaignListDtos, Pageable pageable,Long userId);
>>>>>>> dev
}
