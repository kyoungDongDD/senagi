package com.ssafy.b105.service;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.b105.dto.CampaignRequestDto;
import com.ssafy.b105.dto.CampaignResponseDto;
import com.ssafy.b105.entity.Campaign;
import com.ssafy.b105.entity.CampaignType;
import com.ssafy.b105.entity.Hashtag;
import com.ssafy.b105.entity.QCampaign;
import com.ssafy.b105.entity.QCampaign.*;
import com.ssafy.b105.repository.CampaignRepository;
import com.ssafy.b105.repository.HashtagRepository;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static com.ssafy.b105.entity.QCampaign.campaign;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;


@SpringBootTest
public class CampaignServiceTest {

    @PersistenceContext
    EntityManager em;

    JPAQueryFactory queryFactory;
    @Autowired CampaignService campaignService;

    @Autowired
    CampaignRepository campaignRepository;

    @Autowired
    HashtagRepository hashtagRepository;

    @Test
    @Transactional
    @Rollback(false)
    @DisplayName("캠페인-태그등록테스트")
    public void 캠페인등록() {
        // 서울,부산 태그 추가
        List<String> hashtags = new ArrayList<>();
        hashtags.add("서울");
        hashtags.add("부산");

        // 캠페인1 생성
        CampaignRequestDto campaignPostDto = CampaignRequestDto.builder()
            .type(CampaignType.valueOf("PROJ"))
            .title("경동님을 찾아주세요")
            .thumbnailImageUrl("https://placeimg.com/200/100/any")
            .contentImageUrl("https://placeimg.com/600/100/any")
            .targetDonation(100L)
            .endDate(LocalDateTime.now())
            .hashtags(hashtags)
            .build();


        CampaignResponseDto campaign1 = campaignService.createCampaign(campaignPostDto);

        // 서울,부산 태그 추가
        List<String> hashtags2 = new ArrayList<>();
        hashtags.add("서울");
        hashtags.add("대전");

        // 캠페인2 생성
        CampaignRequestDto campaignPostDto2 = CampaignRequestDto.builder()
            .type(CampaignType.valueOf("SHELTER"))
            .title("동호님을 찾아주세요")
            .thumbnailImageUrl("https://placeimg.com/200/100/any")
            .contentImageUrl("https://placeimg.com/600/100/any")
            .targetDonation(100L)
            .endDate(LocalDateTime.now())
            .hashtags(hashtags)
            .build();

        CampaignResponseDto campaign2 = campaignService.createCampaign(campaignPostDto2);

        List<Hashtag> tagResult = hashtagRepository.findAll();
        List<Campaign> campaignResult = campaignRepository.findAll();
        Assertions.assertThat(tagResult.size()).isEqualTo(3);
        Assertions.assertThat(campaignResult.size()).isEqualTo(2);

    }

    @Test
    @Transactional
    @DisplayName("유닉스변환")
    public void 유닉스변환() {
        System.out.println("시간");
        Long unix =  campaignService.localDateTimeToUnix(LocalDateTime.now());
        System.out.println(unix);
        System.out.println(campaignService.UnixTolocalDateTime(unix));
    }

}