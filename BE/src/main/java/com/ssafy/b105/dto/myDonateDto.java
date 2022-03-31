package com.ssafy.b105.dto;

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
public class myDonateDto {
    private String shelterName;
    private Long amount;
    private String thumnailImagUrl;
    private LocalDateTime donateDate;

}
