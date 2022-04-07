package com.ssafy.b105.repository;


import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.b105.dto.QUserDonateDto;
import com.ssafy.b105.dto.UserDonateDto;
import com.ssafy.b105.entity.QSupportLog;
import com.ssafy.b105.entity.user.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class UserRepositoryImpl implements UserSuppottLogRepository {

    @PersistenceContext
    EntityManager em;

    private final JPAQueryFactory queryFactory;
    private final HashtagRepository hashtagRepository;

    public UserRepositoryImpl(EntityManager em, HashtagRepository hashtagRepository) {
        this.queryFactory = new JPAQueryFactory(em);
        this.hashtagRepository = hashtagRepository;
    }

    @Override
    public List<UserDonateDto> userDonate(User user) {
        List<UserDonateDto> userDonateDtos = queryFactory
            .select( new QUserDonateDto(
                QSupportLog.supportLog.campaign.user.name.as("shelterName"),
                QSupportLog.supportLog.amount,
                QSupportLog.supportLog.campaign.thumbnailImageUrl.as("thumnailImagUrl"),
                QSupportLog.supportLog.donateDate.as("donateDate")
            ))
            .from(QSupportLog.supportLog)
            .where(QSupportLog.supportLog.user.id.eq(user.getId()))
            .fetch();
        return userDonateDtos;
    }

}