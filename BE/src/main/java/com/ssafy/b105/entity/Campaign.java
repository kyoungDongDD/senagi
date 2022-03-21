package com.ssafy.b105.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campaignId")
    private Long id;

    @Column(name = "title", length = 255)
    private String title;

    @Column(name = "thumbnailImageUrl", length = 255)
    private String thumbnailImageUrl;

    @Column(name = "contentImageUrl", length = 255)
    private String contentImageUrl;

    @Column(name = "isEnd")
    private Boolean isEnd;

    @Column(name = "resistDate", length = 30)
    private LocalDateTime resistDate;

    @Column(name = "account", length = 300)
    private String account;

    private Long viewCount;

    private Long targetDonation;

    private LocalDateTime endDate;

    @Enumerated(EnumType.STRING) // 반환타입 int(Default) -> String 으로 바꿔줌
    private CampaignType type;

    @OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<CampaignHashtag> campaignHashtags = new ArrayList<>();

    //==비즈니스 로직==//

    public void addViewCount() {
        this.viewCount += 1;
    }


}