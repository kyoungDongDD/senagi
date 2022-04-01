package com.ssafy.b105.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Builder
@NoArgsConstructor
public class UserDonateDto {
    private String shelterName;
    private Long amount;
    private String thumnailImagUrl;
    @JsonFormat(shape = Shape.STRING,pattern = "yyyy-MM-dd",timezone = "Asia/Seoul")
    private LocalDateTime donateDate;

    @QueryProjection
    public UserDonateDto(String shelterName, Long amount, String thumnailImagUrl,
        LocalDateTime donateDate) {
        this.shelterName = shelterName;
        this.amount = amount;
        this.thumnailImagUrl = thumnailImagUrl;
        this.donateDate = donateDate;
    }
}
