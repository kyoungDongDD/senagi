package com.ssafy.b105.dto;

<<<<<<< HEAD
import com.ssafy.b105.entity.Campaign;
import com.ssafy.b105.entity.CampaignType;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
=======
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.b105.entity.campaign.CampaignType;
import com.ssafy.b105.entity.user.User;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import java.time.LocalDate;
>>>>>>> dev
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
<<<<<<< HEAD


@Getter
=======
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
@Builder
>>>>>>> dev
@AllArgsConstructor
@NoArgsConstructor
public class CampaignRequestDto {

<<<<<<< HEAD
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
=======
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
>>>>>>> dev

    @NotNull
    private CampaignType type;

    @Nullable
    private List<String> hashtags;

<<<<<<< HEAD
=======
    private User user;

    public User addUser(User user){
        this.user=user;
        return user;
    }

>>>>>>> dev
}
