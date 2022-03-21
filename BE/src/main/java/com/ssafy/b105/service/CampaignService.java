package com.ssafy.b105.service;

import com.ssafy.b105.dto.CampaignPostDto;
import com.ssafy.b105.entity.Campaign;
import com.ssafy.b105.entity.CampaignHashtag;
import com.ssafy.b105.entity.Hashtag;
import com.ssafy.b105.repository.CampaignRepository;
import com.ssafy.b105.repository.HashtagRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final HashtagRepository hashtagRepository;


    @Transactional
    public CampaignPostDto createCampaign(CampaignPostDto campaignPostDto) {

        Campaign campaign = Campaign.builder()
            .type(campaignPostDto.getType())
            .title(campaignPostDto.getTitle())
            .thumbnailImageUrl(campaignPostDto.getThumbnailImageUrl())
            .contentImageUrl(campaignPostDto.getContentImageUrl())
            .isEnd(campaignPostDto.getIs_end())
            .targetDonation(campaignPostDto.getTargetDonation())
            .endDate(campaignPostDto.getEndDate())
            .viewCount(campaignPostDto.getViewCount())
            .resistDate(campaignPostDto.getResistDate())
            .account(campaignPostDto.getAccount())
            .build();

        List<Hashtag> hashtags = findAndSaveHashtags(campaignPostDto.getHashtags());

        hashtags.forEach(
            hashtag -> {
                CampaignHashtag campaignHashtag = CampaignHashtag.builder()
                    .campaign(campaign)
                    .hashtag(hashtag)
                    .build();
                campaign.getCampaignHashtags().add(campaignHashtag);
            }
        );

        return CampaignPostDto.from(campaignRepository.save(campaign));
    }

    private List<Hashtag> findAndSaveHashtags(List<String> hashtags) {
        List<Hashtag> results = new ArrayList<>();
        hashtags.forEach(
            tag ->
                results.add(
                    hashtagRepository.findByName(tag)
                        .orElseGet(() ->
                            hashtagRepository.save(Hashtag.builder()
                                .name(tag)
                                .build()
                            )
                        )
                )
        );
        return results;
    }

}
