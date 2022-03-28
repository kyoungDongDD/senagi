package com.ssafy.b105.entity.blockchain;

import java.math.BigInteger;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Transaction {

  @Id
  private String transactionHash;

  private BigInteger transactionIndex;
  private String blockHash;
  private BigInteger blockNumber;
  private BigInteger cumulativeGasUsed;
  private BigInteger gasUsed;
  private String contractAddress;
  private String root;
  // status is only present on Byzantium transactions onwards
  // see EIP 658 https://github.com/ethereum/EIPs/pull/658
  private String status;
  private String from;
  private String to;
  private String logs;
  private String logsBloom;
  private String revertReason;
  private String type;
  private String effectiveGasPrice;

  public static Transaction from(TransactionReceipt receipt) {
    return Transaction.builder()
        .transactionHash(receipt.getTransactionHash())
        .transactionIndex(receipt.getTransactionIndex())
        .blockHash(receipt.getBlockHash())
        .blockNumber(receipt.getBlockNumber())
        .cumulativeGasUsed(receipt.getCumulativeGasUsed())
        .gasUsed(receipt.getGasUsed())
        .contractAddress(receipt.getContractAddress())
        .root(receipt.getRoot())
        .status(receipt.getStatus())
        .from(receipt.getFrom())
        .to(receipt.getTo())
        // List 별도 매핑?
        .logs(receipt.getLogs().toString())
        .logsBloom(receipt.getLogsBloom())
        .revertReason(receipt.getRevertReason())
        .type(receipt.getType())
        .effectiveGasPrice(receipt.getEffectiveGasPrice())
        .build();
  }
}
