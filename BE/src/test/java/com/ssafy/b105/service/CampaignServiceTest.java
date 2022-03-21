package com.ssafy.b105.service;

import com.ssafy.b105.dto.CampaignPostDto;
import com.ssafy.b105.entity.CampaignType;
import com.ssafy.b105.entity.Hashtag;
import com.ssafy.b105.repository.CampaignRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootTest
public class CampaignServiceTest {

    @PersistenceContext
    EntityManager em;

    @Autowired CampaignService campaignService;

    @Autowired
    CampaignRepository campaignRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void testEntity() {

        List<String> hashtags = new ArrayList<>();
        hashtags.add("서울");
        hashtags.add("부산");

        CampaignPostDto campaignPostDto = CampaignPostDto.builder()
            .type(CampaignType.valueOf("PROJ"))
            .title("경동님을 찾아주세요")
            .thumbnailImageUrl("https://placeimg.com/200/100/any")
            .contentImageUrl("https://placeimg.com/600/100/any")
            .account("test")
            .targetDonation(100L)
            .endDate(LocalDateTime.now())
            .hashtags(hashtags)
            .build();


        campaignService.createCampaign(campaignPostDto);

        List<String> hashtags2 = new ArrayList<>();
        hashtags.add("서울");
        hashtags.add("대전");

        CampaignPostDto campaignPostDto2 = CampaignPostDto.builder()
            .type(CampaignType.valueOf("SHELTER"))
            .title("동호님을 찾아주세요")
            .thumbnailImageUrl("https://placeimg.com/200/100/any")
            .contentImageUrl("https://placeimg.com/600/100/any")
            .account("test")
            .targetDonation(100L)
            .endDate(LocalDateTime.now())
            .hashtags(hashtags)
            .build();

        campaignService.createCampaign(campaignPostDto2);

    }
}