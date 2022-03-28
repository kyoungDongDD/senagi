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

    @Nullable
    private Long targetDonation  = 0L;

    @Nullable
    private LocalDateTime endDate = LocalDateTime.of(1,1,1,1,1);

    @NotNull
    private CampaignType type;

    @Nullable
    private List<String> hashtags;

}
