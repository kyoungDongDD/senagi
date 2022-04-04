package com.ssafy.b105.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.b105.entity.campaign.CampaignType;
import com.ssafy.b105.entity.user.User;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CampaignRequestDto {

    private MultipartFile thumbnailImage;
    private MultipartFile contentImage;
    @NotNull
    private String title;

    private String thumbnailImageUrl;

    private String contentImageUrl;

    @Nullable
    private Long targetDonation;

    @Nullable
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @NotNull
    private CampaignType type;

    @Nullable
    private List<String> hashtags;

    private User user;

    public User addUser(User user){
        this.user=user;
        return user;
    }

}
