package com.ssafy.b105.dto;


import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptListDto {
    @NotNull
    private Long campaginId;

    @Nullable
    private List<String> receiptImageUrl;

    //총 출금 금액
    @Builder.Default
    private Long amountAll = 0L;

}
