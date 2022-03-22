package com.ssafy.b105.controller;

import com.ssafy.b105.dto.CampaignRequestDto;
import com.ssafy.b105.dto.CampaignResponseDto;
import com.ssafy.b105.service.CampaignService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/campaign")
public class CampaignController {

    private final CampaignService campaignService;
    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @PostMapping("/project")
    public ResponseEntity<CampaignResponseDto> CreateCampaign(@RequestBody CampaignRequestDto campaignRequestDto) {
        System.out.println("@@@@@@@@@@@@");
        return ResponseEntity.ok(campaignService.createCampaign(campaignRequestDto));
    }

}





