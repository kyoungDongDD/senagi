package com.ssafy.b105.service.blockchain;

import com.ssafy.b105.dto.blockchain.AmountDto;
import com.ssafy.b105.dto.blockchain.ContractCloseResponseDto;
import com.ssafy.b105.dto.blockchain.ContractRequestDto;
import com.ssafy.b105.dto.blockchain.ContractResponseDto;
import com.ssafy.b105.entity.blockchain.Wallet;
import com.ssafy.b105.entity.blockchain.wrapper.campaign.Campaign;
import com.ssafy.b105.entity.blockchain.wrapper.member.Member;
import com.ssafy.b105.entity.blockchain.wrapper.token.Token;
import com.ssafy.b105.utils.BalanceConverter;
import com.ssafy.b105.utils.BlockchainConnector;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;
import org.springframework.stereotype.Service;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

@Service
public class CampaignContractServiceImpl implements
    CampaignContractService {

  private final BlockchainConnector connector;
  private Token tokenMgr;
  private Member memberMgr;
  private BigInteger decimals;

  public CampaignContractServiceImpl(BlockchainConnector connector) {
    this.connector = connector;
    this.tokenMgr = connector.getTokenMgr();
    this.memberMgr = connector.getMemberMgr();
    this.decimals = connector.getDecimals();
  }

  @Override
  public ContractResponseDto deployContract(ContractRequestDto requestDto) {
    try {
      Campaign campaign = connector.deployCampaignContract(requestDto);
      return new ContractResponseDto(campaign.getContractAddress(),
          campaign.getTransactionReceipt().get().getBlockHash());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public AmountDto donate(Wallet from, String contractAccount, Long amount) {
    if(amount <= 0) throw new IllegalArgumentException();
    Campaign campaign = connector.loadContract(contractAccount);

    try {
      TransactionReceipt receipt = campaign.donate(from.getAccount(),
          BalanceConverter.longToBigInteger(amount, decimals)).sendAsync().get();
      return new AmountDto(receipt.getTransactionHash(),
          BalanceConverter.bigIntegerToLong(tokenMgr.balanceOf(contractAccount).sendAsync().get(),decimals));

    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public AmountDto withdrawal(String contractAccount, Wallet to, Long amount) {
    if(amount <= 0) throw new IllegalArgumentException();
    Campaign campaign = connector.loadContract(contractAccount);

    try {
      TransactionReceipt receipt = campaign.withdrawal(to.getAccount(),
          BalanceConverter.longToBigInteger(amount, decimals)).sendAsync().get();
      return new AmountDto(receipt.getTransactionHash(),
          BalanceConverter.bigIntegerToLong(tokenMgr.balanceOf(contractAccount).sendAsync().get(),decimals));
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public ContractCloseResponseDto contractClose(String fromContractAccount, String toContractAccount) {
    Campaign from = connector.loadContract(fromContractAccount);
    Campaign to = connector.loadContract(toContractAccount);
    try {
      TransactionReceipt receipt = from.close(to.getContractAddress()).sendAsync().get();
      return new ContractCloseResponseDto(receipt.getTransactionHash(),
          BalanceConverter.bigIntegerToLong(tokenMgr.balanceOf(fromContractAccount).sendAsync().get(),decimals),
          BalanceConverter.bigIntegerToLong(tokenMgr.balanceOf(toContractAccount).sendAsync().get(),decimals));
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
    return null;
  }
}
