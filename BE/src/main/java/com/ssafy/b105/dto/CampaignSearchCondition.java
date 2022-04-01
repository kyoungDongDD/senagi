package com.ssafy.b105.dto;

import com.ssafy.b105.entity.campaign.CampaignType;
import com.ssafy.b105.entity.campaign.SortType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//캠페인 리스트 파라미터
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CampaignSearchCondition {
    private CampaignType type;
    private Boolean isEnd;
    private Integer page;
    private String searchWord;
    private SortType sortType;
    private Boolean desc;
}
