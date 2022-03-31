package com.ssafy.b105.service;

import com.ssafy.b105.dto.CampaignListDto;
import com.ssafy.b105.dto.CampaignRequestDto;
import com.ssafy.b105.dto.CampaignResponseDto;
import com.ssafy.b105.dto.ReceiptDto;
import com.ssafy.b105.dto.ReceiptPostDto;
import com.ssafy.b105.entity.Receipt;
import com.ssafy.b105.entity.campaign.Campaign;
import com.ssafy.b105.entity.campaign.CampaignHashtag;
import com.ssafy.b105.entity.campaign.Hashtag;
import com.ssafy.b105.auth.Jwt.domain.JwtAuthentication;
import com.ssafy.b105.entity.user.User;
import com.ssafy.b105.dto.ReceiptListDto;
import com.ssafy.b105.dto.blockchain.ContractRequestDto;
import com.ssafy.b105.dto.blockchain.ContractResponseDto;
import com.ssafy.b105.repository.CampaignRepository;
import com.ssafy.b105.repository.HashtagRepository;

import com.ssafy.b105.repository.ReceiptRepository;
import com.ssafy.b105.service.blockchain.CampaignContractService;
import com.ssafy.b105.utils.MD5Generator;
import com.ssafy.b105.utils.TimeConverter;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.b105.repository.UserRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Builder
public class CampaignService {
    private final CampaignRepository campaignRepository;
    private final HashtagRepository hashtagRepository;
    private final UserRepository userRepository;
    private final ReceiptRepository receiptRepository;
    private final CampaignContractService campaignContractService;
    @Transactional
    public CampaignResponseDto createCampaignProject(JwtAuthentication jwtAuthentication,
        CampaignRequestDto campaignRequestDto) throws ChangeSetPersister.NotFoundException {

        //todo: 로그인된 토큰에서 principal 뽑는것 알아야함
        User user = userRepository.findById(jwtAuthentication.getId())
            .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        campaignRequestDto.addUser(user); // 캠페인 테이블 유저컬럼에 유저 저장

        String thumbnailOriginalFilename = campaignRequestDto.getThumbnailImage()
            .getOriginalFilename();
        String contentOriginalFilename = campaignRequestDto.getContentImage().getOriginalFilename();
        //파일 확장자
        String thumbnailExtension = thumbnailOriginalFilename.substring(
            thumbnailOriginalFilename.lastIndexOf("."));
        String contentExtension = contentOriginalFilename.substring(
            contentOriginalFilename.lastIndexOf("."));

        try {
            String thumbnailOriginFilename = campaignRequestDto.getThumbnailImage()
                .getOriginalFilename();
            String contentOriginFilename = campaignRequestDto.getContentImage().getOriginalFilename();

            String thumbnailFilename = new MD5Generator(thumbnailOriginFilename).toString();
            String contentFilename = new MD5Generator(contentOriginFilename).toString();

            thumbnailFilename += thumbnailExtension;
            contentFilename += contentExtension;

            /* 실행되는 위치의 'receiptImage' 폴더에 파일이 저장됩니다. */
            String savePath = System.getProperty("user.dir") + "\\imgs";
            /* 파일이 저장되는 폴더가 없으면 폴더를 생성합니다. */
            if (!new File(savePath).exists()) {
                try {
                    new File(savePath).mkdir();
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
            //파일 경로
            String thumbnailFilePath = savePath + "\\" + thumbnailFilename;
            String contentFilePath = savePath + "\\" + contentFilename;
            campaignRequestDto.getThumbnailImage().transferTo(new File(thumbnailFilePath));
            campaignRequestDto.getContentImage().transferTo(new File(contentFilePath));

            campaignRequestDto.setContentImageUrl(contentFilePath);
            campaignRequestDto.setThumbnailImageUrl(thumbnailFilePath);

            Campaign campaign = Campaign.from(campaignRequestDto);

            List<Hashtag> hashtags = findAndSaveHashtags(campaignRequestDto.getHashtags());

            hashtags.forEach(hashtag -> {
                CampaignHashtag campaignHashtag = CampaignHashtag.builder().campaign(campaign)
                    .hashtag(hashtag).build();
                campaign.getCampaignHashtags().add(campaignHashtag);
            });
            return CampaignResponseDto.from(campaignRepository.save(campaign));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Transactional
    public CampaignResponseDto createCampaignShelter(JwtAuthentication jwtAuthentication,
        CampaignRequestDto campaignRequestDto) throws ChangeSetPersister.NotFoundException {
        //todo: 로그인된 토큰에서 principal 뽑는것 알아야함
        User user = userRepository.findById(jwtAuthentication.getId())
            .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        campaignRequestDto.addUser(user); // 캠페인 테이블 유저컬럼에 유저 저장

        Campaign campaign = Campaign.from(campaignRequestDto);

        List<Hashtag> hashtags = findAndSaveHashtags(campaignRequestDto.getHashtags());

        hashtags.forEach(hashtag -> {
            CampaignHashtag campaignHashtag = CampaignHashtag.builder().campaign(campaign)
                .hashtag(hashtag).build();
            campaign.getCampaignHashtags().add(campaignHashtag);
        });

        return CampaignResponseDto.from(campaignRepository.save(campaign));
    }


    @Transactional
    public CampaignResponseDto detailCampaign(Campaign campaign) {
        //조회수 증가
        campaign.addViewCount();
        //id로 캠페인 찾기
        return CampaignResponseDto.from(campaign);
    }

    //해쉬태그 저장 및 중복검사
    private List<Hashtag> findAndSaveHashtags(List<String> hashtags) {
        List<Hashtag> results = new ArrayList<>();
        hashtags.forEach(tag -> results.add(hashtagRepository.findByName(tag)
            .orElseGet(() -> hashtagRepository.save(Hashtag.builder().name(tag).build()))));
        return results;
    }

    public ReceiptListDto receiptList(Campaign campaign) {
        Long allAmount = campaignRepository.receiptAllAmount(campaign);
        ReceiptListDto receiptListDto = ReceiptListDto.builder().campaginId(campaign.getId())
            .receiptImageUrl(campaign.getReceiptList()).amountAll(allAmount).build();
        return receiptListDto;
    }

    public List<CampaignListDto> findAllByUserId(Long userId) {
        List<CampaignListDto> campaignListDtos = new ArrayList<>();
        for (Campaign campaign : campaignRepository.findAllByUserId(userId)) {

            CampaignListDto dto = CampaignListDto.builder().id(campaign.getId())
                .title(campaign.getTitle()).thumbnailImageUrl(campaign.getThumbnailImageUrl())
                .isEnd(campaign.getIsEnd()).viewCount(campaign.getViewCount())
                .targetDonation(campaign.getTargetDonation()).type(campaign.getType())
                .registDate(campaign.getRegistDate())
                .lastModifiedDate(campaign.getLastModifiedDate()).build();

            campaignListDtos.add(dto);
        }
        return campaignListDtos;
    }

}
