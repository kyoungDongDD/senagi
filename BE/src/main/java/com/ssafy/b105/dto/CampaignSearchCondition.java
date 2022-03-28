package com.ssafy.b105.dto;

import com.ssafy.b105.entity.CampaignType;
import com.ssafy.b105.entity.SortType;
import lombok.Data;

//캠페인 리스트 파라미터
@Data
public class CampaignSearchCondition {
    private CampaignType type;
    private Boolean isEnd;
    private Integer page;
    private String searchWord;
    private SortType sortType;
    private Boolean desc;
}
