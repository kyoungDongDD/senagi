package com.ssafy.b105.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.b105.dto.blockchain.ContractRequestDto;
import com.ssafy.b105.dto.blockchain.NewWalletDto;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.web3j.campaign.Campaign;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Wallet;
import org.web3j.crypto.WalletFile;
import org.web3j.crypto.WalletUtils;
import org.web3j.member.Member;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.token.Token;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

@Component
@RequiredArgsConstructor
public class BlockchainConnector {

  private final Web3j web3j;

  @Value("${eth.key.path}")
  private String keyPath;

  // Owner account
  @Value("${eth.admin.wallet}")
  private String adminWallet;

  @Value("${eth.admin.password}")
  private String adminPass;

  @Value("${eth.gas.limit}")
  private String gasLimit;

  @Value("${eth.gas.price}")
  private String gasPrice;

  @Value("${eth.contract.member}")
  private String memberAddr;

  @Value("${eth.contract.token}")
  private String tokenAddr;

  private Member memberMgr;
  private Token tokenMgr;
  private Credentials credentials;
  private ContractGasProvider gasProvider;


  @PostConstruct
  public void init() {
    this.gasProvider = new StaticGasProvider(
        new BigInteger(gasPrice),
        new BigInteger(gasLimit));

    try {
      File wallet = ResourceUtils.getFile(keyPath + adminWallet);
      credentials = WalletUtils.loadCredentials("pass0", wallet);
      this.memberMgr = Member.load(memberAddr, web3j, credentials, gasProvider);
      this.tokenMgr = Token.load(tokenAddr, web3j, credentials, gasProvider);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (CipherException e) {
      e.printStackTrace();
    }


  }

  public Campaign deployCampaignContract(ContractRequestDto dto) throws Exception {
    return Campaign.deploy(
        web3j,
        credentials,
        gasProvider,
        dto.getTargetAmount(),
        dto.getDeadLine(),
        memberAddr,
        tokenAddr).send();
  }
  public Campaign loadContract(String address) {
    return Campaign.load(address,web3j,credentials,gasProvider);
  }

  public Member getMemberMgr() {
    return memberMgr;
  }

  public Token getTokenMgr() {
    return tokenMgr;
  }

  public String getOwnerAccount() {
    return credentials.getAddress();
  }

  public BigInteger getDecimals() {
    try {
      return tokenMgr.decimals().sendAsync().get();
      // TODO EXCEPTION 처리 해야함
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
    return new BigInteger("18");
  }

  public EthBlockNumber getBlockNumber()
      throws ExecutionException, InterruptedException {
    return web3j.ethBlockNumber().sendAsync().get();
  }

  public NewWalletDto createAccount()
      throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, CipherException, IOException {

    String password = createPassword();

    ECKeyPair ecKeyPair = Keys.createEcKeyPair();

    WalletFile walletFile = Wallet.createStandard(password, ecKeyPair);

    String fileName = saveWalletFile(walletFile);

    return new NewWalletDto(walletFile.getAddress(),fileName,password);
  }

  private String getWalletFileName(WalletFile walletFile) {
    DateTimeFormatter format =
        DateTimeFormatter.ofPattern("'UTC--'yyyy-MM-dd'T'HH-mm-ss.nVV'--'");
    ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);

    return now.format(format) + walletFile.getAddress() + ".json";
  }

  private String saveWalletFile(WalletFile walletFile) throws IOException {
    String fileName = getWalletFileName(walletFile);
    File file = new File(ResourceUtils.getFile(keyPath), fileName);
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.writeValue(file,walletFile);

    return fileName;
  }

  private String createPassword() {
    return UUID.randomUUID().toString().replaceAll("-","");
  }

}
