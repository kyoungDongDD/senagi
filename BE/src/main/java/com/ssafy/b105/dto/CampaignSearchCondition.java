package com.ssafy.b105.dto;

<<<<<<< HEAD
import com.ssafy.b105.entity.CampaignType;
import com.ssafy.b105.entity.SortType;
=======
import com.ssafy.b105.entity.campaign.CampaignType;
import com.ssafy.b105.entity.campaign.SortType;
>>>>>>> dev
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//캠페인 리스트 파라미터
<<<<<<< HEAD
//Todo data 수정
@Getter
//Setter 가 있어야함
=======
@Getter
>>>>>>> dev
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
