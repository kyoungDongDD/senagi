package com.ssafy.b105.dto;

import static com.ssafy.b105.entity.campaign.QCampaign.campaign;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.querydsl.core.annotations.QueryProjection;

import com.ssafy.b105.entity.campaign.Campaign;
import com.ssafy.b105.entity.campaign.CampaignType;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String shelterName;

    @JsonFormat(shape = Shape.STRING,pattern = "yyyy-MM-dd",timezone = "Asia/Seoul")
    private LocalDateTime endDate;

    @JsonFormat(shape = Shape.STRING,pattern = "yyyy-MM-dd",timezone = "Asia/Seoul")
    private LocalDateTime registDate;

    @JsonFormat(shape = Shape.STRING,pattern = "yyyy-MM-dd",timezone = "Asia/Seoul")
    private LocalDateTime lastModifiedDate;

    @JsonIgnore
    private String account;

    @Setter
    private Long balance;
    

}