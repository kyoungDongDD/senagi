package com.ssafy.b105.dto;

import com.ssafy.b105.entity.Campaign;
import com.ssafy.b105.entity.CampaignType;
import com.sun.istack.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CampaignPostDto {

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
    private Boolean is_end = false;

    @Builder.Default
    private LocalDateTime resistDate = LocalDateTime.now();

    private String account;

    @Builder.Default
    private Long viewCount = 0L;

    private Long targetDonation;

    private LocalDateTime endDate;

    @NotNull
    private CampaignType type;

    private List<String> hashtags;

    public static CampaignPostDto from(Campaign campaign) {
        if (campaign == null) {
            return null;
        }

        return CampaignPostDto.builder()
            .type(campaign.getType())
            .title(campaign.getTitle())
            .targetDonation(campaign.getTargetDonation())
            .contentImageUrl(campaign.getContentImageUrl())
            .targetDonation(campaign.getTargetDonation())
            .endDate(campaign.getEndDate())
            .build();
    }
}
