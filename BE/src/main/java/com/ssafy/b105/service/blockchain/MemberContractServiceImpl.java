package com.ssafy.b105.service.blockchain;

import com.ssafy.b105.entity.blockchain.wrapper.member.Member;
import com.ssafy.b105.entity.common.MemberType;
import com.ssafy.b105.utils.BlockchainConnector;
import java.util.concurrent.ExecutionException;
import org.springframework.stereotype.Service;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

@Service
public class MemberContractServiceImpl implements
    MemberContractService {

  private final BlockchainConnector connector;

  private Member memberMgr;

  public MemberContractServiceImpl(BlockchainConnector connector) {
    this.connector = connector;
    this.memberMgr = connector.getMemberMgr();
  }

  @Override
  public boolean registMember(String account, MemberType type) {
    try {
      TransactionReceipt receipt = memberMgr.newMember(account, type.toString())
          .sendAsync().get();
      return true;
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public boolean isTransferPossible(String from, String to) {
    try {
      return memberMgr.isTransferPossible(from,to).sendAsync().get();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public boolean isMember(String account) {
    try {
      return memberMgr.isMember(account).sendAsync().get();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public boolean isWithdrawalPossible(String account) {
    try {
      return memberMgr.isWithdrawalPossible(account).sendAsync().get();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public boolean isSupporter(String account) {
    try {
      return memberMgr.isSupporter(account).sendAsync().get();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public boolean isShelter(String account) {
    try {
      return memberMgr.isShelter(account).sendAsync().get();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public boolean isProjectCampaign(String account) {
    try {
      return memberMgr.isPrjoectCampaign(account).sendAsync().get();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public boolean isShelterCampaign(String account) {
    try {
      return memberMgr.isShelterCampaign(account).sendAsync().get();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
    return false;
  }
}
