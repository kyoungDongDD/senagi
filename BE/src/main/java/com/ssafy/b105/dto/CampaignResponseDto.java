package com.ssafy.b105.dto;

import com.ssafy.b105.entity.Campaign;
import com.ssafy.b105.entity.CampaignType;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CampaignResponseDto {

    @NotNull
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String thumbnailImageUrl;

    @NotNull
    private String contentImageUrl;

    @NotNull
    @Builder.Default
    private Boolean isEnd = false;

    @Builder.Default
    private LocalDateTime resistDate = LocalDateTime.now();

    @Builder.Default
    private Long viewCount = 0L;

    @Nullable
    private Long targetDonation;

    @Nullable
    private LocalDateTime endDate;

    //@NotNull
    private String account;

    @NotNull
    private CampaignType type;

    private LocalDateTime lastModifiedDate;


    public static CampaignResponseDto from(Campaign campaign) {
        if (campaign == null) {
            return null;
        }

        return CampaignResponseDto.builder()
            .id(campaign.getId())
            .thumbnailImageUrl(campaign.getThumbnail_image_url())
            .type(campaign.getType())
            .account(campaign.getAccount())
            .title(campaign.getTitle())
            .viewCount(campaign.getView_count())
            .resistDate(campaign.getRegist_date())
            .targetDonation(campaign.getTarget_donation())
            .contentImageUrl(campaign.getContent_image_url())
            .targetDonation(campaign.getTarget_donation())
            .endDate(campaign.getEnd_date())
            .lastModifiedDate(campaign.getLast_modified_date())
            .build();
    }
}