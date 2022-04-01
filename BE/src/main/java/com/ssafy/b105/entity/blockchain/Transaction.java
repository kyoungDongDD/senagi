package com.ssafy.b105.entity.blockchain;

import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Transaction {

  @Id
  private String hash;

  private String nonce;
  private String blockHash;
  private BigInteger blockNumber;
  private BigInteger transactionIndex;
  private String fromAccount;
  private String toAccount;
  private BigInteger value;
  private BigInteger gasPrice;
  private BigInteger gas;
  @Lob
  private String inputValue;
  private String creates;
  private String publicKey;
  private String raw;
  private String r;
  private String s;
  private long v; // see https://github.com/web3j/web3j/issues/44
  private String type;
  private String maxFeePerGas;
  private String maxPriorityFeePerGas;

  public static Transaction from(org.web3j.protocol.core.methods.response.Transaction transaction) {
    return Transaction.builder()
        .hash(transaction.getHash())
        .transactionIndex(transaction.getTransactionIndex())
        .blockHash(transaction.getBlockHash())
        .blockNumber(transaction.getBlockNumber())
        .nonce(transaction.getNonceRaw())
        .fromAccount(transaction.getFrom())
        .toAccount(transaction.getTo())
        .value(transaction.getValue())
        .gasPrice(transaction.getGasPrice())
        .gas(transaction.getGas())
        .inputValue(transaction.getInput())
        .creates(transaction.getCreates())
        .publicKey(transaction.getPublicKey())
        .raw(transaction.getRaw())
        .r(transaction.getR())
        .v(transaction.getV())
        .type(transaction.getType())
        .maxFeePerGas(transaction.getMaxFeePerGas())
        .maxPriorityFeePerGas(transaction.getMaxPriorityFeePerGas())

        .build();
  }
}
