package com.ssafy.b105.controller;

<<<<<<< HEAD
=======
import com.querydsl.core.Tuple;
>>>>>>> dev
import com.ssafy.b105.dto.CampaignListDto;
import com.ssafy.b105.dto.CampaignRequestDto;
import com.ssafy.b105.dto.CampaignResponseDto;
import com.ssafy.b105.dto.CampaignSearchCondition;
<<<<<<< HEAD
import com.ssafy.b105.dto.HashtagDto;
import com.ssafy.b105.entity.Campaign;
import com.ssafy.b105.entity.CampaignHashtag;
=======
import com.ssafy.b105.entity.campaign.Campaign;
import com.ssafy.b105.auth.Jwt.domain.JwtAuthentication;
>>>>>>> dev
import com.ssafy.b105.repository.CampaignRepository;
import com.ssafy.b105.service.CampaignService;
import java.util.List;
import lombok.RequiredArgsConstructor;
<<<<<<< HEAD
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
=======
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
>>>>>>> dev
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
<<<<<<< HEAD
=======
@Slf4j
>>>>>>> dev
public class CampaignController {

    private final CampaignService campaignService;
    private final CampaignRepository campaignRepository;

    @PostMapping("/api/campaign/project")
<<<<<<< HEAD
    public ResponseEntity<CampaignResponseDto> CreateCampaign(@RequestBody CampaignRequestDto campaignRequestDto) {
        return ResponseEntity.ok(campaignService.createCampaign(campaignRequestDto));
=======
    public ResponseEntity<CampaignResponseDto> CreateCampaignProject(
        CampaignRequestDto campaignRequestDto,
        @AuthenticationPrincipal JwtAuthentication jwtAuthentication)
        throws ChangeSetPersister.NotFoundException {
        log.debug("[CreateCampaignProject]{} jwtAuthentication Notfound");
        return ResponseEntity.ok(
            campaignService.createCampaignProject(jwtAuthentication, campaignRequestDto));
>>>>>>> dev
    }

    //detail 조회
    @GetMapping("/api/campaign/detail/{id}")
<<<<<<< HEAD
    public ResponseEntity<CampaignResponseDto> DetailCampaign(@PathVariable("id") Campaign campaign) {
        return ResponseEntity.ok(campaignService.detailCampaign(campaign));
    }
=======
    public ResponseEntity<CampaignResponseDto> DetailCampaign(
        @PathVariable("id") Campaign campaign) {
        return ResponseEntity.ok(campaignService.detailCampaign(campaign));
    }

    //캠페인 태그 조회
>>>>>>> dev
    @GetMapping("/api/campaign/tags/{id}")
    public ResponseEntity<List<String>> CampaignTag(@PathVariable("id") Campaign campaign) {
        return ResponseEntity.ok(campaign.getHashtag());
    }
<<<<<<< HEAD
    //캠페인 리스트 조회
    @GetMapping("/api/campaigns")
    public Page<CampaignListDto> CampaginList(CampaignSearchCondition condition, Pageable pageable){
        return campaignRepository.searchList(condition , pageable);
=======

    //캠페인 리스트 조회
    @GetMapping("/api/campaigns")
    public ResponseEntity<Page<CampaignListDto>> CampaginList(CampaignSearchCondition condition,
        Pageable pageable) {
        return ResponseEntity.ok(campaignRepository.searchList(condition, pageable));
    }

    @GetMapping("/api/campaigns/owned")
    public ResponseEntity<Page<CampaignListDto>> FindMyCampaign(Pageable pageable,
        @AuthenticationPrincipal JwtAuthentication jwtAuthentication) {
        return ResponseEntity.ok(campaignService.findAllByUserId(jwtAuthentication.getId(),pageable));
>>>>>>> dev
    }
}





