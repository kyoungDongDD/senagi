package com.ssafy.b105.service.blockchain;

import com.ssafy.b105.dto.blockchain.ContractRequestDto;
import com.ssafy.b105.dto.blockchain.ContractResponseDto;
import com.ssafy.b105.entity.common.MemberType;
import com.ssafy.b105.repository.UserRepository;
import com.ssafy.b105.utils.BlockchainConnector;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.campaign.Campaign;

@SpringBootTest
@Transactional
class CampaignContractServiceTest {

  @Autowired
  CampaignContractService campaignContractService;

  @Autowired
  UserRepository userRepository;

  @Autowired
  MemberContractService memberContractService;

  @Autowired
  BlockchainConnector connector;

  @Test
  public void 프로젝트_캠페인_생성() {
    // given
    // 2022-03-27 16:44:08 / 1000만원
    ContractRequestDto requestDto = new ContractRequestDto(
        1648367048L,
        100000000L
    );

    ContractResponseDto contract = campaignContractService.deployContract(requestDto);

    // 보호소 캠페인일 경우 MemberType.ShleterCampaign
    memberContractService.registMember(contract.getAccount(), MemberType.ProjectCampaign);
    // when

    Campaign findCampaign = connector.loadContract(contract.getAccount());
    // then
    Assertions.assertThat(findCampaign.getContractAddress()).isEqualTo(contract.getAccount());
  }

}