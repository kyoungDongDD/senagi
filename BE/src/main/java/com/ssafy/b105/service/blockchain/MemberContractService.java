package com.ssafy.b105.service.blockchain;

import com.ssafy.b105.entity.blockchain.Wallet;
import com.ssafy.b105.entity.common.MemberType;

/**
 * 블록체인 회원 Contract와 통신하는 서비스
 * 블록체인에 회원등록 시행
 * 회원 분류는 {@link com.ssafy.b105.entity.common.MemberType} 참고
 */
public interface MemberContractService {

  boolean registMember(String account, MemberType type);
  boolean isTransferPossible(String from, String to);
  boolean isMember(String account);
  boolean isWithdrawalPossible(String account);
  boolean isSupporter(String account);
  boolean isShelter(String account);
  boolean isProjectCampaign(String account);
  boolean isShelterCampaign(String account);
}
