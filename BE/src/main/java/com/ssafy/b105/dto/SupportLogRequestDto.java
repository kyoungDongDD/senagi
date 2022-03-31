package com.ssafy.b105.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SupportLogRequestDto {
    private Long userId;
    private Long campaignId;
    private Long amount;
}
