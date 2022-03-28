package com.ssafy.b105.service;

import com.ssafy.b105.dto.CampaignRequestDto;
import com.ssafy.b105.dto.CampaignResponseDto;
import com.ssafy.b105.entity.Campaign;
import com.ssafy.b105.entity.CampaignHashtag;
import com.ssafy.b105.entity.Hashtag;
import com.ssafy.b105.repository.CampaignRepository;
import com.ssafy.b105.repository.HashtagRepository;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Builder
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final HashtagRepository hashtagRepository;


    @Transactional
    public CampaignResponseDto createCampaign(CampaignRequestDto campaignRequestDto) {

        Campaign campaign = Campaign.from(campaignRequestDto);

        List<Hashtag> hashtags = findAndSaveHashtags(campaignRequestDto.getHashtags());

        hashtags.forEach(
            hashtag -> {
                CampaignHashtag campaignHashtag = CampaignHashtag.builder()
                    .campaign(campaign)
                    .hashtag(hashtag)
                    .build();
                campaign.getCampaignHashtags().add(campaignHashtag);
            }
        );

        return CampaignResponseDto.from(campaignRepository.save(campaign));
    }

    @Transactional
    public CampaignResponseDto detailCampaign(Long id) {
        //조회수 증가
        campaignRepository.addViewCount(id);
        //id로 캠페인 찾기
        Optional<Campaign> campaign = campaignRepository.findById(id);
        return CampaignResponseDto.from(campaign.get());
    }

    //해쉬태그 저장 및 중복검사
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

    //localDateTime => UnixTime
    public Long localDateTimeToUnix(LocalDateTime localDateTime) {
        return Timestamp.valueOf(localDateTime).getTime();

    }

    //UnixTime => localDateTime
    public LocalDateTime UnixTolocalDateTime(Long UnixTime) {
        LocalDateTime systemLocalDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(UnixTime), TimeZone.getDefault().toZoneId());
        return systemLocalDateTime;

    }
}
