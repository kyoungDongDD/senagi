package com.ssafy.b105.dto;

import com.ssafy.b105.entity.campaign.Campaign;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptDto {

    private Long id;

    private String receiptImageUrl;

    private Long amount;

    private String txHash;

    private Campaign campaign;


}
