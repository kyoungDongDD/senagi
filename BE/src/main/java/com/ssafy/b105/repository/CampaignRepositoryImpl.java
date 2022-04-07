package com.ssafy.b105.repository;



import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.b105.dto.CampaignListDto;
import com.ssafy.b105.dto.CampaignSearchCondition;
import com.ssafy.b105.entity.campaign.*;
import com.ssafy.b105.entity.QReceipt;
import com.ssafy.b105.service.blockchain.TokenContractService;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import static com.ssafy.b105.entity.campaign.QCampaign.campaign;

public class CampaignRepositoryImpl implements CampaignSearchRepository {

    @PersistenceContext
    EntityManager em;

    private final JPAQueryFactory queryFactory;
    private final HashtagRepository hashtagRepository;
    private final TokenContractService tokenContractService;

    public CampaignRepositoryImpl(EntityManager em, HashtagRepository hashtagRepository,TokenContractService tokenContractService) {
        this.queryFactory = new JPAQueryFactory(em);
        this.hashtagRepository = hashtagRepository;
        this.tokenContractService = tokenContractService;
    }

    @Override
    public Long receiptAllAmount(Campaign campaign) {

        Long result = queryFactory
            .select(QReceipt.receipt.amount.sum())
            .from(QReceipt.receipt)
            .where(QReceipt.receipt.campaign.id.eq(campaign.getId()))
            .fetchOne();
        return result;
    }
        // 검색 , 조회 , 정렬쿼리
    @Override
    public Page<CampaignListDto> searchList(CampaignSearchCondition condition, Pageable pageable) {

        List<CampaignListDto> content = queryFactory
            .select(Projections.fields(CampaignListDto.class,
                campaign.id,
                campaign.title,
                campaign.thumbnailImageUrl,
                campaign.isEnd,
                campaign.viewCount,
                campaign.targetDonation,
                campaign.endDate,
                campaign.type,
                campaign.registDate,
                campaign.lastModifiedDate,
                campaign.user.name,
                campaign.account
            )).distinct()
            .from(campaign)
            .leftJoin(campaign.campaignHashtags, QCampaignHashtag.campaignHashtag)
            .leftJoin(QCampaignHashtag.campaignHashtag.hashtag,QHashtag.hashtag)
            .where(
                typeEq(condition.getType()),
                isEndEq(condition.getIsEnd()),
                //제목 or 태그에 검색어가 있으면
                (searchTitle(condition.getSearchWord())).or(searchTag(condition.getSearchWord())))
            .orderBy(sortConditon(condition.getSortType(), condition.getDesc()))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        for(CampaignListDto campaignListDto : content){
            Long balance = tokenContractService.balanceOf(campaignListDto.getAccount());
            campaignListDto.setBalance(balance);
        }

        JPAQuery<Long> countQuery = queryFactory
            .select(campaign.count())
            .from(campaign)
            .where(typeEq(condition.getType()),
                isEndEq(condition.getIsEnd()),
                //제목 or 태그에 검색어가 있으면
                (searchTitle(condition.getSearchWord())).or(searchTag(condition.getSearchWord()))
            );

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    // 여기서부터 List 정렬, 검색 조건 함수
    // where절에 null 반환시 조건 생략
    private BooleanExpression typeEq(CampaignType type) {
        return (type == null)||(type == CampaignType.ALL)? null : campaign.type.eq(type);
    }

    private BooleanExpression isEndEq(Boolean isEnd) {
        return isEnd == null ? null : campaign.isEnd.eq(isEnd);
    }

    private BooleanExpression searchTitle(String searchWord) {
        if (searchWord == null) {
            searchWord = "";
        }
        return  campaign.title.contains(searchWord);
    }
    private BooleanExpression searchTag(String searchWord) {
        if (searchWord == null) {
            return null;
        }
        return QHashtag.hashtag.name.contains(searchWord);
    }

    //정렬 조건
    private OrderSpecifier sortConditon(SortType sortType, Boolean desc) {
        if (desc == null) {
            desc = false;
        }
        if (sortType == null) {
            if (desc) {
                return campaign.id.desc();
            }
            return campaign.registDate.asc();
        }

        //정렬 조건
        switch (sortType) {
            case endDate:
                if (desc) {
                    return campaign.endDate.desc();
                }
                return campaign.endDate.asc();
            case viewCount:
                if (desc) {
                    return campaign.viewCount.desc();
                }
                return campaign.viewCount.asc();
            case registDate:
                if (desc) {
                    return campaign.registDate.desc();
                }
                return campaign.registDate.asc();
            case id:
                if (desc) {
                    return campaign.id.desc();
                }
                return campaign.registDate.asc();
            default:
                return null;
        }
    }

    @Override
    public Page<CampaignListDto> myCampaign(List<CampaignListDto> campaignListDtos, Pageable pageable,Long userId) {
        JPAQuery<Long> countQuery = queryFactory
            .select(campaign.count())
            .from(campaign)
            .where(campaign.id.eq(userId));
        return PageableExecutionUtils.getPage(campaignListDtos, pageable, countQuery::fetchOne);
    }

}