package com.ssafy.b105.service.blockchain;

import com.ssafy.b105.dto.blockchain.ContractRequestDto;
import com.ssafy.b105.dto.blockchain.ContractResponseDto;
import com.ssafy.b105.entity.blockchain.Wallet;

public interface CampaignContractService {

  ContractResponseDto deployContract(ContractRequestDto requestDto);

  void donate(Wallet from, String to, Long amount);

  void withdrawal(String contractAccount, Wallet to, Long amount);

  void contractClose(String fromContractAccount, String toContractAccount);
}
