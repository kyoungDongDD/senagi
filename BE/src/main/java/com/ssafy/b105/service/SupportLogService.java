package com.ssafy.b105.service;

import com.ssafy.b105.dto.SupportLogRequestDto;
import com.ssafy.b105.dto.SupportLogResponseDto;
import com.ssafy.b105.dto.blockchain.AmountDto;
import com.ssafy.b105.entity.SupportLog;
import com.ssafy.b105.entity.TokenLog;
import com.ssafy.b105.entity.blockchain.Wallet;
import com.ssafy.b105.entity.campaign.Campaign;
import com.ssafy.b105.entity.user.User;
import com.ssafy.b105.repository.CampaignRepository;
import com.ssafy.b105.repository.SupportLogRepository;
import com.ssafy.b105.repository.TokenLogRepository;
import com.ssafy.b105.repository.UserRepository;
import com.ssafy.b105.service.blockchain.CampaignContractService;
import com.ssafy.b105.service.blockchain.TokenContractService;
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

    @Autowired
    private CampaignContractService campaignContractService;

    @Autowired
    private TokenContractService tokenContractService;

    @Autowired
    private TokenLogRepository tokenLogRepository;

    public SupportLogResponseDto donation(SupportLogRequestDto supportLogDto,Long userId){

        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException());
        Campaign campaign = campaignRepository.findById(supportLogDto.getCampaignId())
            .orElseThrow(() -> new IllegalArgumentException());

        // TODO Exception 수정
        if(campaign.getIsEnd()) {
            throw new IllegalArgumentException();
        }

        Wallet wallet = user.getWallet();

        AmountDto chargeDto = tokenContractService.charge(wallet, supportLogDto.getAmount());

        tokenLogRepository.save(TokenLog.of(chargeDto.getAmount(), chargeDto.getTransactionHash(), user));

        Long balance = tokenContractService.balanceOf(wallet);
        if(balance < supportLogDto.getAmount())
            throw new IllegalArgumentException();

        AmountDto donate = campaignContractService.donate(
            user.getWallet(),
            campaign.getAccount(),
            supportLogDto.getAmount());
        if(campaignContractService.isEnd(campaign.getAccount()))
            campaign.setIsEnd(true);

        wallet.setBalance(tokenContractService.balanceOfBigInteger(wallet));
        SupportLog supportLog = SupportLog.builder()
            .campaign(campaignRepository.findById(supportLogDto.getCampaignId()).get())
            .user(userRepository.findById(userId).get())
            .amount(donate.getAmount())
            .txHash(donate.getTransactionHash())
            .build();

        return SupportLogResponseDto.from(supportLogRepository.save(supportLog));

    }
}
