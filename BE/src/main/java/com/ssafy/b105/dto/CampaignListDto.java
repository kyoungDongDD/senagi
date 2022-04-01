package com.ssafy.b105.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.querydsl.core.annotations.QueryProjection;

import com.ssafy.b105.entity.campaign.Campaign;
import com.ssafy.b105.entity.campaign.CampaignType;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CampaignListDto {

    private Long id;
    private String title;
    private String thumbnailImageUrl;
    private Boolean isEnd ;
    private Long viewCount;
    private Long targetDonation;
    private CampaignType type;

    @JsonFormat(shape = Shape.STRING,pattern = "yyyy-MM-dd",timezone = "Asia/Seoul")
    private LocalDateTime endDate;

    @JsonFormat(shape = Shape.STRING,pattern = "yyyy-MM-dd",timezone = "Asia/Seoul")
    private LocalDateTime registDate;

    @JsonFormat(shape = Shape.STRING,pattern = "yyyy-MM-dd",timezone = "Asia/Seoul")
    private LocalDateTime lastModifiedDate;

    // 프로젝션과결과반환을 위한 어노테이션 @QueryProjection
    // 생성자 위에 사용해야함
    @QueryProjection
    public CampaignListDto(Long id, String title, String thumbnailImageUrl, Boolean isEnd,
        Long viewCount, Long targetDonation, LocalDateTime endDate,
        CampaignType type, LocalDateTime registDate, LocalDateTime lastModifiedDate) {
        this.id = id;
        this.title = title;
        this.thumbnailImageUrl = thumbnailImageUrl;
        this.isEnd = isEnd;
        this.viewCount = viewCount;
        this.targetDonation = targetDonation;
        this.endDate = endDate;
        this.type = type;
        this.registDate = registDate;
        this.lastModifiedDate = lastModifiedDate;
    }

}