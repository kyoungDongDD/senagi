package com.ssafy.b105.dto;

import com.ssafy.b105.entity.Campaign;
import com.ssafy.b105.entity.CampaignType;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
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
public class CampaignRequestDto {

    @NotNull
    private String title;

    @NotNull
    private String thumbnailImageUrl;

    @NotNull
    private String contentImageUrl;

    @NotNull
    @Builder.Default
    private Boolean isEnd = false;

    @Nullable
    @Builder.Default
    private Long targetDonation  = 0L;

    @Builder.Default
    private LocalDateTime endDate = LocalDateTime.of(11111,1,1,1,1);

    @NotNull
    @Builder.Default
    private Long viewCount = 0L;

    @Builder.Default
    private LocalDateTime resistDate = LocalDateTime.now();

    @NotNull
    private CampaignType type;

    @Nullable
    private List<String> hashtags;

    private static CampaignRequestDto from(Campaign campaign) {
        if (campaign == null) {
            return null;
        }

        return CampaignRequestDto.builder()
            .type(campaign.getType())
            .title(campaign.getTitle())
            .thumbnailImageUrl(campaign.getThumbnail_image_url())
            .targetDonation(campaign.getTarget_donation())
            .contentImageUrl(campaign.getContent_image_url())
            .targetDonation(campaign.getTarget_donation())
            .endDate(campaign.getEnd_date())
            .build();
    }
}
