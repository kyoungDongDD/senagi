package com.ssafy.b105.entity.blockchain.wrapper.member;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.8.8.
 */
@SuppressWarnings("rawtypes")
public class Member extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50610033610025640100000000610038810204565b64010000000061003c810204565b61008c565b3390565b60008054600160a060020a03838116600160a060020a0319831681178455604051919092169283917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e09190a35050565b610a948061009b6000396000f3fe608060405234801561001057600080fd5b50600436106100c6576000357c010000000000000000000000000000000000000000000000000000000090048063715018a61161008e578063715018a6146102355780638da5cb5b1461023f578063a230c52414610263578063b9cccee314610289578063de68e472146102af578063f2fde38b146102d5576100c6565b80630e66139f146100cb578063355415d1146101955780634212eddb146101bb57806346f72076146101e957806361b3ad8f1461020f575b600080fd5b610181600480360360408110156100e157600080fd5b600160a060020a03823516919081019060408101602082013564010000000081111561010c57600080fd5b82018360208201111561011e57600080fd5b8035906020019184600183028401116401000000008311171561014057600080fd5b91908080601f0160208091040260200160405190810160405280939291908181526020018383808284376000920191909152509295506102fb945050505050565b604080519115158252519081900360200190f35b610181600480360360208110156101ab57600080fd5b5035600160a060020a0316610414565b610181600480360360408110156101d157600080fd5b50600160a060020a0381358116916020013516610436565b610181600480360360208110156101ff57600080fd5b5035600160a060020a03166105b9565b6101816004803603602081101561022557600080fd5b5035600160a060020a03166105df565b61023d6105e8565b005b61024761066b565b60408051600160a060020a039092168252519081900360200190f35b6101816004803603602081101561027957600080fd5b5035600160a060020a031661067a565b6101816004803603602081101561029f57600080fd5b5035600160a060020a0316610699565b610181600480360360208110156102c557600080fd5b5035600160a060020a03166106a2565b61023d600480360360208110156102eb57600080fd5b5035600160a060020a03166106ab565b6000610305610776565b600160a060020a031661031661066b565b600160a060020a031614610374576040805160e560020a62461bcd02815260206004820181905260248201527f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e6572604482015290519081900360640190fd5b600061037f8461077a565b600481111561038a57fe5b146103c95760405160e560020a62461bcd02815260040180806020018281038252602a815260200180610a35602a913960400191505060405180910390fd5b6103d282610798565b600160a060020a03841660009081526001602081905260409091208054909160ff199091169083600481111561040457fe5b0217905550600190505b92915050565b600060045b6104228361077a565b600481111561042d57fe5b1490505b919050565b600061044061066b565b600160a060020a031683600160a060020a031614156104615750600161040e565b600161046c8461077a565b600481111561047757fe5b14156104e75760046104888361077a565b600481111561049357fe5b14806104b2575060036104a58361077a565b60048111156104b057fe5b145b806104d557506104c061066b565b600160a060020a031682600160a060020a0316145b156104e25750600161040e565b6105b0565b60036104f28461077a565b60048111156104fd57fe5b141561054557600461050e8361077a565b600481111561051957fe5b14806104d5575060025b61052c8361077a565b600481111561053757fe5b14156104e25750600161040e565b60046105508461077a565b600481111561055b57fe5b1415610568576002610523565b60026105738461077a565b600481111561057e57fe5b1480156105a3575061058e61066b565b600160a060020a031682600160a060020a0316145b156105b05750600161040e565b50600092915050565b600060016105c68361077a565b60048111156105d157fe5b148061040e57506002610419565b60006002610419565b6105f0610776565b600160a060020a031661060161066b565b600160a060020a03161461065f576040805160e560020a62461bcd02815260206004820181905260248201527f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e6572604482015290519081900360640190fd5b61066960006108ca565b565b600054600160a060020a031690565b6000806106868361077a565b600481111561069157fe5b141592915050565b60006001610419565b60006003610419565b6106b3610776565b600160a060020a03166106c461066b565b600160a060020a031614610722576040805160e560020a62461bcd02815260206004820181905260248201527f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e6572604482015290519081900360640190fd5b600160a060020a03811661076a5760405160e560020a62461bcd028152600401808060200182810382526026815260200180610a0f6026913960400191505060405180910390fd5b610773816108ca565b50565b3390565b600160a060020a031660009081526001602052604090205460ff1690565b60006107d9826040518060400160405280600f81526020017f50726f6a65637443616d706169676e0000000000000000000000000000000000815250610927565b156107e657506003610431565b610825826040518060400160405280600f81526020017f5368656c74657243616d706169676e0000000000000000000000000000000000815250610927565b1561083257506004610431565b610871826040518060400160405280600781526020017f5368656c74657200000000000000000000000000000000000000000000000000815250610927565b1561087e57506002610431565b6108bd826040518060400160405280600981526020017f537570706f727465720000000000000000000000000000000000000000000000815250610927565b156100c657506001610431565b60008054600160a060020a0383811673ffffffffffffffffffffffffffffffffffffffff19831681178455604051919092169283917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e09190a35050565b6000816040516020018082805190602001908083835b6020831061095c5780518252601f19909201916020918201910161093d565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405160208183030381529060405280519060200120836040516020018082805190602001908083835b602083106109ca5780518252601f1990920191602091820191016109ab565b6001836020036101000a038019825116818451168082178552505050505050905001915050604051602081830303815290604052805190602001201490509291505056fe4f776e61626c653a206e6577206f776e657220697320746865207a65726f20616464726573734d656d626572206572726f72203a2054686973206164647265737320616c726561647920657869737473a264697066735822122035ff95161140e582c034843a2cd88a75a368490bd7d980f8ffce6e640daeea9764736f6c63430007010033";

    public static final String FUNC_ISMEMBER = "isMember";

    public static final String FUNC_ISPRJOECTCAMPAIGN = "isPrjoectCampaign";

    public static final String FUNC_ISSHELTER = "isShelter";

    public static final String FUNC_ISSHELTERCAMPAIGN = "isShelterCampaign";

    public static final String FUNC_ISSUPPORTER = "isSupporter";

    public static final String FUNC_ISTRANSFERPOSSIBLE = "isTransferPossible";

    public static final String FUNC_ISWITHDRAWALPOSSIBLE = "isWithdrawalPossible";

    public static final String FUNC_NEWMEMBER = "newMember";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    @Deprecated
    protected Member(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Member(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Member(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Member(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<OwnershipTransferredEventResponse> getOwnershipTransferredEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, transactionReceipt);
        ArrayList<OwnershipTransferredEventResponse> responses = new ArrayList<OwnershipTransferredEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, OwnershipTransferredEventResponse>() {
            @Override
            public OwnershipTransferredEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, log);
                OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
                typedResponse.log = log;
                typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OWNERSHIPTRANSFERRED_EVENT));
        return ownershipTransferredEventFlowable(filter);
    }

    public RemoteFunctionCall<Boolean> isMember(String addr) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISMEMBER, 
                Arrays.<Type>asList(new Address(160, addr)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Boolean> isPrjoectCampaign(String addr) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISPRJOECTCAMPAIGN, 
                Arrays.<Type>asList(new Address(160, addr)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Boolean> isShelter(String addr) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISSHELTER, 
                Arrays.<Type>asList(new Address(160, addr)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Boolean> isShelterCampaign(String addr) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISSHELTERCAMPAIGN, 
                Arrays.<Type>asList(new Address(160, addr)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Boolean> isSupporter(String addr) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISSUPPORTER, 
                Arrays.<Type>asList(new Address(160, addr)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Boolean> isTransferPossible(String from, String to) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISTRANSFERPOSSIBLE, 
                Arrays.<Type>asList(new Address(160, from),
                new Address(160, to)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Boolean> isWithdrawalPossible(String addr) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISWITHDRAWALPOSSIBLE, 
                Arrays.<Type>asList(new Address(160, addr)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> newMember(String account, String memberType) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_NEWMEMBER, 
                Arrays.<Type>asList(new Address(160, account),
                new org.web3j.abi.datatypes.Utf8String(memberType)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> owner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> renounceOwnership() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_RENOUNCEOWNERSHIP, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferOwnership(String newOwner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(new Address(160, newOwner)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static Member load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Member(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Member load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Member(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Member load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Member(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Member load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Member(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Member> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Member.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Member> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Member.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Member> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Member.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Member> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Member.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class OwnershipTransferredEventResponse extends BaseEventResponse {
        public String previousOwner;

        public String newOwner;
    }
}
