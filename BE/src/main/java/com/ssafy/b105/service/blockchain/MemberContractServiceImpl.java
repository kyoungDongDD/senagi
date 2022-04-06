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
      TransactionReceipt receipt = memberMgr.newMember(account, type.toString()).send();
      return true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public boolean isTransferPossible(String from, String to) {
    try {
      return memberMgr.isTransferPossible(from, to).send();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public boolean isMember(String account) {
    try {
      return memberMgr.isMember(account).send();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public boolean isWithdrawalPossible(String account) {
    try {
      return memberMgr.isWithdrawalPossible(account).send();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public boolean isSupporter(String account) {
    try {
      return memberMgr.isSupporter(account).send();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public boolean isShelter(String account) {
    try {
      return memberMgr.isShelter(account).send();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public boolean isProjectCampaign(String account) {
    try {
      return memberMgr.isPrjoectCampaign(account).send();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public boolean isShelterCampaign(String account) {
    try {
      return memberMgr.isShelterCampaign(account).send();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }
}
