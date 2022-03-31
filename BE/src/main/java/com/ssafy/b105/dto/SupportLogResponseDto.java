package com.ssafy.b105.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.ssafy.b105.entity.SupportLog;
import com.ssafy.b105.entity.campaign.Campaign;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SupportLogResponseDto {
    private Long userId;
    private Long campaignId;
    private Long amount;

    @JsonFormat(shape = Shape.STRING,pattern = "yyyy-MM-dd",timezone = "Asia/Seoul")
    private LocalDateTime donationDate;

    public static SupportLogResponseDto from(SupportLog supportLog) {
        if (supportLog == null) {
            return null;
        }
        return SupportLogResponseDto.builder()
            .userId(supportLog.getUser().getId())
            .campaignId(supportLog.getCampaign().getId())
            .amount(supportLog.getAmount())
            .donationDate(supportLog.getDonationDate())
            .build();
    }
}
