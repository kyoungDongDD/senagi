package com.ssafy.b105.controller;

import com.ssafy.b105.dto.CampaignPostDto;
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
    public ResponseEntity<CampaignPostDto> roll(@RequestBody CampaignPostDto campaignPostDto) {
        return ResponseEntity.ok(campaignService.createCampaign(campaignPostDto));
    }

}





