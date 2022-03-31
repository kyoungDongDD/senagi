package com.ssafy.b105.service;

import com.ssafy.b105.dto.SupportLogRequestDto;
import com.ssafy.b105.dto.SupportLogResponseDto;
import com.ssafy.b105.entity.SupportLog;
import com.ssafy.b105.repository.CampaignRepository;
import com.ssafy.b105.repository.SupportLogRepository;
import com.ssafy.b105.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SupportLogService {

    @Autowired
    private CampaignRepository campaignRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SupportLogRepository supportLogRepository;

    public SupportLogResponseDto donation(SupportLogRequestDto supportLogDto){
        SupportLog supportLog = SupportLog.builder()
            .campaign(campaignRepository.findById(supportLogDto.getCampaignId()).get())
            .user(userRepository.findById(supportLogDto.getUserId()).get())
            .amount(supportLogDto.getAmount())
            .build();

        return SupportLogResponseDto.from(supportLogRepository.save(supportLog));

    }
}
