package com.ssafy.b105.repository;

import com.ssafy.b105.entity.blockchain.Wallet;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface WalletRepository extends CrudRepository<Wallet, Long> {

  Optional<Wallet> findWalletByMemberId(Long memberId);
}
