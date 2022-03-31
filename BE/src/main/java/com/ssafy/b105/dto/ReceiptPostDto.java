package com.ssafy.b105.dto;


import com.ssafy.b105.entity.Receipt;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptPostDto {

    private Long id;

    private String receiptImageUrl;

    private String item;

    private Long amount;

    private Long campaignId;

    public static ReceiptPostDto from(Receipt receipt) {
        if(receipt == null) return null;

        return ReceiptPostDto.builder()
            .id(receipt.getId())
            .receiptImageUrl(receipt.getReceiptImageUrl())
            .item(receipt.getItem())
            .amount(receipt.getAmount())
            .campaignId(receipt.getCampaign().getId())
            .build();
    }
}
