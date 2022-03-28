package com.ssafy.b105.service.blockchain;

import com.ssafy.b105.dto.blockchain.ContractRequestDto;
import com.ssafy.b105.dto.blockchain.ContractResponseDto;
import com.ssafy.b105.entity.blockchain.Wallet;
import com.ssafy.b105.utils.BalanceConverter;
import com.ssafy.b105.utils.BlockchainConnector;
import java.math.BigInteger;
import org.springframework.stereotype.Service;
import org.web3j.campaign.Campaign;
import org.web3j.member.Member;
import org.web3j.token.Token;

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
  public void donate(Wallet from, String contractAccount, Long amount) {
    if(amount <= 0) throw new IllegalArgumentException();
    Campaign campaign = connector.loadContract(contractAccount);

    campaign.donate(from.getAccount(),
        BalanceConverter.longToBigInteger(amount,decimals));

  }

  @Override
  public void withdrawal(String contractAccount, Wallet to, Long amount) {
    if(amount <= 0) throw new IllegalArgumentException();
    Campaign campaign = connector.loadContract(contractAccount);

    campaign.withdrawal(to.getAccount(),
        BalanceConverter.longToBigInteger(amount,decimals));
  }

  @Override
  public void contractClose(String fromContractAccount, String toContractAccount) {
    Campaign from = connector.loadContract(fromContractAccount);
    Campaign to = connector.loadContract(toContractAccount);
    from.close(to.getContractAddress());
  }
}
