package com.ssafy.b105.entity;

import com.ssafy.b105.dto.CampaignRequestDto;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Campaign extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campaign_id")
    private Long id;

    @Column(length = 255)
    private String title;

    @Column(length = 255)
    private String thumbnailImageUrl;

    @Column(length = 255)
    private String contentImageUrl;

    @Builder.Default
    private Boolean isEnd = false; //종료 여부 true: 종료됨 ,false : 진행중

    @Column(length = 300)
    private String account; //블록체인 계좌정보

    @Builder.Default
    private Long viewCount = 0L; //조회수

    @Builder.Default
    private Long targetDonation = 0L;

    @Builder.Default
    private LocalDateTime endDate = LocalDateTime.of(11111,1,1,1,1);

    @Enumerated(EnumType.STRING) 
    private CampaignType type;

    @OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<CampaignHashtag> campaignHashtags = new ArrayList<>();

    //==비즈니스 로직==//

    //조회수 증가 함수
    public void addViewCount() {
        this.viewCount += 1;
    }

    public static Campaign from(CampaignRequestDto campaignRequestDto) {
        if (campaignRequestDto == null) {
            return null;
        }

        return Campaign.builder()
            .title(campaignRequestDto.getTitle())
            .thumbnailImageUrl(campaignRequestDto.getThumbnailImageUrl())
            .targetDonation(campaignRequestDto.getTargetDonation())
            .contentImageUrl(campaignRequestDto.getContentImageUrl())
            .isEnd(campaignRequestDto.getIsEnd())
            .targetDonation(campaignRequestDto.getTargetDonation())
            .endDate(campaignRequestDto.getEndDate())
            .type(campaignRequestDto.getType())
            .build();
    }
}