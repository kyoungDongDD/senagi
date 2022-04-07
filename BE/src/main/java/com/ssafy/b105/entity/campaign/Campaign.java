package com.ssafy.b105.entity.campaign;

import com.ssafy.b105.dto.CampaignRequestDto;
import com.ssafy.b105.dto.blockchain.ContractResponseDto;
import com.ssafy.b105.entity.SupportLog;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.*;

import com.ssafy.b105.entity.BaseEntity;
import com.ssafy.b105.entity.Receipt;
import com.ssafy.b105.entity.user.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Campaign extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campaign_id")
    private Long id;

    @Column(length = 255)
    private String title;

    private String thumbnailImageUrl;

    private String contentImageUrl;

    @Builder.Default
    private Boolean isEnd = false; //종료 여부 true: 종료됨 ,false : 진행중

    @Column(length = 42)
    private String account; //블록체인 계좌정보

    @Column(length = 66)
    private String blockHash;

    @Builder.Default
    private Long viewCount = 0L; //조회수

    @Builder.Default
    private Long targetDonation = 0L;

    @Builder.Default
    private LocalDateTime endDate = LocalDateTime.of(1,1,1,1,1);

    @Enumerated(EnumType.STRING) 
    private CampaignType type;

    //연관 관계 매핑
    @OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<CampaignHashtag> campaignHashtags = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Receipt> receipts = new ArrayList<>();

    @OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<SupportLog> supportLogs = new ArrayList<>();

    //==비즈니스 로직==//

    //조회수 증가 함수
    public void addViewCount() {
        this.viewCount += 1;
    }

    public List<String> getHashtag(){
        return campaignHashtags.stream()
            .map(campaignHashtags -> campaignHashtags.getHashtag().getName())
            .collect(Collectors.toList());
    }
    public List<String> getReceiptList(){
        return receipts.stream()
            .map(receipt -> receipt.getReceiptImageUrl())
            .collect(Collectors.toList());
    }


    public static Campaign of(CampaignRequestDto campaignRequestDto, ContractResponseDto contractResponseDto) {
        if (campaignRequestDto == null) {
            return null;
        }

        return Campaign.builder()
            .title(campaignRequestDto.getTitle())
            .thumbnailImageUrl(campaignRequestDto.getThumbnailImageUrl())
            .targetDonation(campaignRequestDto.getTargetDonation())
            .contentImageUrl(campaignRequestDto.getContentImageUrl())
            .targetDonation(campaignRequestDto.getTargetDonation())
            .endDate(campaignRequestDto.getEndDate())
            .type(campaignRequestDto.getType())
            .user(campaignRequestDto.getUser())
            .account(contractResponseDto.getAccount())
            .blockHash(contractResponseDto.getBlockHash())
            .build();
    }
}