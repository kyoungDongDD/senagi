//package com.ssafy.b105.service.blockchain;
//
//import com.ssafy.b105.entity.blockchain.Wallet;
//import com.ssafy.b105.entity.common.MemberType;
//import com.ssafy.b105.repository.UserRepository;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//
//@SpringBootTest
//@Transactional
//@Rollback(value = false)
//class MemberContractServiceImplTest {
//
//  @Autowired
//  WalletService walletService;
//
//  @Autowired
//  MemberContractService memberContractService;
//
//  @Autowired
//  UserRepository userRepository;
//
//  @Test
//  public void 멤버등록() throws NotFoundException {
//    // given
//    User user = userRepository.findOneByName("동동스")
//        .orElseThrow(() -> new NotFoundException());
//    Wallet wallet = walletService.createAccount(user)
//        .orElseThrow(() -> new NotFoundException());
//
//    // when
//    memberContractService.registMember(wallet.getAccount(), MemberType.Supporter);
//    boolean result = memberContractService.isSupporter(wallet.getAccount());
//
//    // then
//    Assertions.assertThat(result).isTrue();
//  }
//}