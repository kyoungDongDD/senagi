package com.ssafy.b105.service.blockchain;

import com.ssafy.b105.dto.blockchain.AmountDto;
import com.ssafy.b105.dto.blockchain.ContractCloseResponseDto;
import com.ssafy.b105.dto.blockchain.ContractRequestDto;
import com.ssafy.b105.dto.blockchain.ContractResponseDto;
import com.ssafy.b105.entity.blockchain.Wallet;

public interface CampaignContractService {

  ContractResponseDto deployContract(ContractRequestDto requestDto);

  AmountDto donate(Wallet from, String to, Long amount);

  AmountDto withdrawal(String contractAccount, Wallet to, Long amount);

  boolean isEnd(String contractAccount);

  ContractCloseResponseDto contractClose(String fromContractAccount, String toContractAccount);
}
