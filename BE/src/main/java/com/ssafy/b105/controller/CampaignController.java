package com.ssafy.b105.controller;

import com.ssafy.b105.dto.CampaignListDto;
import com.ssafy.b105.dto.CampaignRequestDto;
import com.ssafy.b105.dto.CampaignResponseDto;
import com.ssafy.b105.dto.CampaignSearchCondition;
import com.ssafy.b105.dto.HashtagDto;
import com.ssafy.b105.entity.Campaign;
import com.ssafy.b105.repository.CampaignRepository;
import com.ssafy.b105.service.CampaignService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CampaignController {

    private final CampaignService campaignService;
    private final CampaignRepository campaignRepository;

    @PostMapping("/api/campaign/project")
    public ResponseEntity<CampaignResponseDto> CreateCampaign(@RequestBody CampaignRequestDto campaignRequestDto) {
        return ResponseEntity.ok(campaignService.createCampaign(campaignRequestDto));
    }

    //detail 조회
    @GetMapping("/api/campaign/detail/{id}")
    public ResponseEntity<CampaignResponseDto> DetailCampaign(@PathVariable("id") Long id) {
        return ResponseEntity.ok(campaignService.detailCampaign(id));
    }
    @GetMapping("/api/campaign/tags/{id}")
    public ResponseEntity<List<HashtagDto>> DetailCampaign(@PathVariable("id") Campaign campaign) {
        return ResponseEntity.ok(campaignRepository.searchTag(campaign));
    }
    //캠페인 리스트 조회
    @GetMapping("/api/campaigns")
    public Page<CampaignListDto> CampaginList(CampaignSearchCondition condition, Pageable pageable){
        return campaignRepository.searchList(condition , pageable);
    }
}





