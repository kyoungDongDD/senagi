package com.ssafy.b105.entity.blockchain.wrapper.token;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
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
public class Token extends Contract {
    public static final String BINARY = "60806040526007805460ff191660121790553480156200001e57600080fd5b5060405162001bd838038062001bd8833981810160405260608110156200004457600080fd5b81019080805160405193929190846401000000008211156200006557600080fd5b9083019060208201858111156200007b57600080fd5b82516401000000008111828201881017156200009657600080fd5b82525081516020918201929091019080838360005b83811015620000c5578181015183820152602001620000ab565b50505050905090810190601f168015620000f35780820380516001836020036101000a031916815260200191505b50604052602001805160405193929190846401000000008211156200011757600080fd5b9083019060208201858111156200012d57600080fd5b82516401000000008111828201881017156200014857600080fd5b82525081516020918201929091019080838360005b83811015620001775781810151838201526020016200015d565b50505050905090810190601f168015620001a55780820380516001836020036101000a031916815260200191505b50604052602001519150620001d79050620001c86401000000006200025b810204565b6401000000006200025f810204565b8251620001ec9060059060208601906200040c565b508151620002029060069060208501906200040c565b5060078054601260ff19909116179081905560048054600160a060020a031916600160a060020a0384161790556200025290339060ff16600a0a64e8d4a5100002640100000000620002af810204565b505050620004a8565b3390565b60008054600160a060020a03838116600160a060020a0319831681178455604051919092169283917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e09190a35050565b600160a060020a03821662000310576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252602681526020018062001bb26026913960400191505060405180910390fd5b600081116200038057604080517f08c379a000000000000000000000000000000000000000000000000000000000815260206004820152601860248201527f546f6b656e4572726f72203a20616d6f756e74203c3d20300000000000000000604482015290519081900360640190fd5b6001805482019055600160a060020a0382166000818152600260205260409020805483019055620003b9640100000000620003fd810204565b600160a060020a03167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef836040518082815260200191505060405180910390a35050565b600054600160a060020a031690565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200044f57805160ff19168380011785556200047f565b828001600101855582156200047f579182015b828111156200047f57825182559160200191906001019062000462565b506200048d92915062000491565b5090565b5b808211156200048d576000815560010162000492565b6116fa80620004b86000396000f3fe608060405234801561001057600080fd5b50600436106101045760003560e060020a90048063715018a61161009b578063bbc2b0261161006a578063bbc2b02614610314578063dbcaef8b14610340578063dd62ed3e1461036c578063f2fde38b1461039a57610104565b8063715018a6146102b25780638da5cb5b146102bc57806395d89b41146102e0578063a9059cbb146102e857610104565b8063313ce567116100d7578063313ce5671461021657806339dbcb6314610234578063421f14611461026057806370a082311461028c57610104565b806306fdde0314610109578063095ea7b31461018657806318160ddd146101c657806323b872dd146101e0575b600080fd5b6101116103c0565b6040805160208082528351818301528351919283929083019185019080838360005b8381101561014b578181015183820152602001610133565b50505050905090810190601f1680156101785780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b6101b26004803603604081101561019c57600080fd5b50600160a060020a038135169060200135610456565b604080519115158252519081900360200190f35b6101ce61059a565b60408051918252519081900360200190f35b6101b2600480360360608110156101f657600080fd5b50600160a060020a038135811691602081013590911690604001356105a0565b61021e61068e565b6040805160ff9092168252519081900360200190f35b6101b26004803603604081101561024a57600080fd5b50600160a060020a038135169060200135610697565b6101b26004803603604081101561027657600080fd5b50600160a060020a038135169060200135610824565b6101ce600480360360208110156102a257600080fd5b5035600160a060020a0316610a15565b6102ba610a30565b005b6102c4610aa1565b60408051600160a060020a039092168252519081900360200190f35b610111610ab0565b6101b2600480360360408110156102fe57600080fd5b50600160a060020a038135169060200135610b11565b6101b26004803603604081101561032a57600080fd5b50600160a060020a038135169060200135610bf4565b6101b26004803603604081101561035657600080fd5b50600160a060020a038135169060200135610d3c565b6101ce6004803603604081101561038257600080fd5b50600160a060020a0381358116916020013516610e7d565b6102ba600480360360208110156103b057600080fd5b5035600160a060020a0316610eb8565b60058054604080516020601f600260001961010060018816150201909516949094049384018190048102820181019092528281526060939092909183018282801561044c5780601f106104215761010080835404028352916020019161044c565b820191906000526020600020905b81548152906001019060200180831161042f57829003601f168201915b5050505050905090565b6000600460009054906101000a9004600160a060020a0316600160a060020a031663a230c524846040518263ffffffff1660e060020a0281526004018082600160a060020a0316815260200191505060206040518083038186803b1580156104bd57600080fd5b505afa1580156104d1573d6000803e3d6000fd5b505050506040513d60208110156104e757600080fd5b50516105275760405160e560020a62461bcd0281526004018080602001828103825260328152602001806114f76032913960400191505060405180910390fd5b6000821161057f576040805160e560020a62461bcd02815260206004820152601860248201527f546f6b656e4572726f72203a20616d6f756e74203c3d20300000000000000000604482015290519081900360640190fd5b6105918361058b610aa1565b84610f71565b50600192915050565b60015490565b600160a060020a03831660009081526003602052604081208290826105c3610aa1565b600160a060020a0316600160a060020a031681526020019081526020016000205410156106245760405160e560020a62461bcd02815260040180806020018281038252602e815260200180611697602e913960400191505060405180910390fd5b61062f8484846110bd565b6106848461063b610aa1565b600160a060020a0387166000908152600360205260408120869161065d610aa1565b600160a060020a0316600160a060020a031681526020019081526020016000205403610f71565b5060019392505050565b60075460ff1690565b60006106a161139a565b600160a060020a03166106b2610aa1565b600160a060020a0316146106fe576040805160e560020a62461bcd0281526020600482018190526024820152600080516020611553833981519152604482015290519081900360640190fd5b60008211610756576040805160e560020a62461bcd02815260206004820152601b60248201527f546f6b656e4572726f72203a20737562416d6f756e74203c3d20300000000000604482015290519081900360640190fd5b600160a060020a038316600090815260036020526040812083908261077961139a565b600160a060020a03168152602081019190915260400160002054039050600160a060020a0384166000908152600360205260408120906107b761139a565b600160a060020a0316600160a060020a03168152602001908152602001600020548111156108195760405160e560020a62461bcd0281526004018080602001828103825260318152602001806114c66031913960400191505060405180910390fd5b610684843383610f71565b600061082e61139a565b600160a060020a031661083f610aa1565b600160a060020a03161461088b576040805160e560020a62461bcd0281526020600482018190526024820152600080516020611553833981519152604482015290519081900360640190fd5b600082116108e3576040805160e560020a62461bcd02815260206004820152601b60248201527f546f6b656e4572726f72203a20616464416d6f756e74203c3d20300000000000604482015290519081900360640190fd5b600160a060020a038316600090815260036020526040812083908261090661139a565b600160a060020a0316600160a060020a03168152602001908152602001600020540190506003600085600160a060020a0316600160a060020a03168152602001908152602001600020600061095961139a565b600160a060020a0316600160a060020a03168152602001908152602001600020548110156109bb5760405160e560020a62461bcd0281526004018080602001828103825260308152602001806115ef6030913960400191505060405180910390fd5b600160a060020a0384166000908152600260205260409020548111156108195760405160e560020a62461bcd02815260040180806020018281038252602881526020018061144e6028913960400191505060405180910390fd5b600160a060020a031660009081526002602052604090205490565b610a3861139a565b600160a060020a0316610a49610aa1565b600160a060020a031614610a95576040805160e560020a62461bcd0281526020600482018190526024820152600080516020611553833981519152604482015290519081900360640190fd5b610a9f600061139e565b565b600054600160a060020a031690565b60068054604080516020601f600260001961010060018816150201909516949094049384018190048102820181019092528281526060939092909183018282801561044c5780601f106104215761010080835404028352916020019161044c565b6000600460009054906101000a9004600160a060020a0316600160a060020a031663a230c524846040518263ffffffff1660e060020a0281526004018082600160a060020a0316815260200191505060206040518083038186803b158015610b7857600080fd5b505afa158015610b8c573d6000803e3d6000fd5b505050506040513d6020811015610ba257600080fd5b5051610be25760405160e560020a62461bcd0281526004018080602001828103825260328152602001806114f76032913960400191505060405180910390fd5b610591610bed61139a565b84846110bd565b6000610bfe61139a565b600160a060020a0316610c0f610aa1565b600160a060020a031614610c5b576040805160e560020a62461bcd0281526020600482018190526024820152600080516020611553833981519152604482015290519081900360640190fd5b600460009054906101000a9004600160a060020a0316600160a060020a03166346f72076846040518263ffffffff1660e060020a0281526004018082600160a060020a0316815260200191505060206040518083038186803b158015610cc057600080fd5b505afa158015610cd4573d6000803e3d6000fd5b505050506040513d6020811015610cea57600080fd5b5051610d2a5760405160e560020a62461bcd02815260040180806020018281038252602e815260200180611573602e913960400191505060405180910390fd5b61059183610d36610aa1565b846110bd565b6000610d4661139a565b600160a060020a0316610d57610aa1565b600160a060020a031614610da3576040805160e560020a62461bcd0281526020600482018190526024820152600080516020611553833981519152604482015290519081900360640190fd5b600460009054906101000a9004600160a060020a0316600160a060020a031663a230c524846040518263ffffffff1660e060020a0281526004018082600160a060020a0316815260200191505060206040518083038186803b158015610e0857600080fd5b505afa158015610e1c573d6000803e3d6000fd5b505050506040513d6020811015610e3257600080fd5b5051610e725760405160e560020a62461bcd0281526004018080602001828103825260328152602001806114f76032913960400191505060405180910390fd5b610591610bed610aa1565b6000610e87610aa1565b600160a060020a03808516600090815260036020908152604080832093851683529290522054909250905092915050565b610ec061139a565b600160a060020a0316610ed1610aa1565b600160a060020a031614610f1d576040805160e560020a62461bcd0281526020600482018190526024820152600080516020611553833981519152604482015290519081900360640190fd5b600160a060020a038116610f655760405160e560020a62461bcd0281526004018080602001828103825260268152602001806114766026913960400191505060405180910390fd5b610f6e8161139e565b50565b600160a060020a038316610fb95760405160e560020a62461bcd02815260040180806020018281038252602a815260200180611529602a913960400191505060405180910390fd5b600160a060020a0382166110015760405160e560020a62461bcd0281526004018080602001828103825260288152602001806116436028913960400191505060405180910390fd5b600160a060020a03831660009081526002602052604090205481111561105b5760405160e560020a62461bcd02815260040180806020018281038252602481526020018061161f6024913960400191505060405180910390fd5b600160a060020a03808416600081815260036020908152604080832094871680845294825291829020859055815185815291517f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b9259281900390910190a3505050565b60048054604080517f4212eddb000000000000000000000000000000000000000000000000000000008152600160a060020a0387811694820194909452858416602482015290519290911691634212eddb91604480820192602092909190829003018186803b15801561112f57600080fd5b505afa158015611143573d6000803e3d6000fd5b505050506040513d602081101561115957600080fd5b50516111995760405160e560020a62461bcd0281526004018080602001828103825260278152602001806113fc6027913960400191505060405180910390fd5b600160a060020a0383166111e15760405160e560020a62461bcd02815260040180806020018281038252602b815260200180611423602b913960400191505060405180910390fd5b600160a060020a0382166112295760405160e560020a62461bcd0281526004018080602001828103825260298152602001806115a16029913960400191505060405180910390fd5b6000811161126b5760405160e560020a62461bcd02815260040180806020018281038252602a81526020018061149c602a913960400191505060405180910390fd5b600160a060020a038084166000908152600260205260408082205492851682529020548201828210156112d25760405160e560020a62461bcd02815260040180806020018281038252602c81526020018061166b602c913960400191505060405180910390fd5b600160a060020a03841660009081526002602052604090205481101561132c5760405160e560020a62461bcd0281526004018080602001828103825260258152602001806115ca6025913960400191505060405180910390fd5b600160a060020a03808616600081815260026020908152604080832080548990039055938816808352918490208054880190558351878152935191937fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef929081900390910190a35050505050565b3390565b60008054600160a060020a0383811673ffffffffffffffffffffffffffffffffffffffff19831681178455604051919092169283917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e09190a3505056fe546f6b656e4572726f72203a2066726f6d202d3e20746f206973206e6f7420706f737369626c65546f6b656e4572726f72203a207472616e736665722066726f6d20746865207a65726f2061646472657373546f6b656e4572726f72203a205468697320616d6f756e7420657863656564732062616c616e63654f776e61626c653a206e6577206f776e657220697320746865207a65726f2061646472657373546f6b656e4572726f72203a20616d6f756e74206d7573742062652067726561746572207468616e2030546f6b656e4572726f72203a20446563726561736520617070726f766520756e646572666c6f7720657863657074696f6e546f6b656e4572726f72203a2054686973206163636f756e74206973206e6f742072656769737465726564206d656d626572546f6b656e4572726f72203a20617070726f76652066726f6d20746865207a65726f20616464726573734f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e6572546f6b656e4572726f72203a2054686973206163636f756e742063616e6e6f742062652077697468647261776e6c546f6b656e4572726f72203a207472616e7366657220746f20746865207a65726f2061646472657373546f6b656e4572726f72203a207472616e7366657220616d6f756e74206f766572666c6f77546f6b656e4572726f72203a20496e63726561736520617070726f7665206f766572666c6f7720657863657074696f6e546f6b656e4572726f72203a20616d6f756e7420657863656564732062616c616e636573546f6b656e4572726f72203a20617070726f766520746f20746865207a65726f2061646472657373546f6b656e4572726f72203a207472616e7366657220616d6f756e7420657863656564732062616c616e6365546f6b656e4572726f72203a207472616e7366657220616d6f756e74206578636565647320616c6c6f77616e6365a264697066735822122016c0327c8823c81d50edd193e1f1b1c23a01e056b4de5a713ffc7001ba26d84464736f6c63430007010033546f6b656e4572726f72203a205f6d696e7420746f20746865207a65726f2061646472657373";

    public static final String FUNC_ALLOWANCE = "allowance";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_CHARGETOKEN = "chargeToken";

    public static final String FUNC_DECIMALS = "decimals";

    public static final String FUNC_DECREASEAPPROVE = "decreaseApprove";

    public static final String FUNC_INCREASEAPPROVE = "increaseApprove";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_SYMBOL = "symbol";

    public static final String FUNC_TOTALSUPPLY = "totalSupply";

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_TRANSFERFROM = "transferFrom";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final String FUNC_WITHDRAWALTOKEN = "withdrawalToken";

    public static final Event APPROVAL_EVENT = new Event("Approval", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event TRANSFER_EVENT = new Event("Transfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected Token(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Token(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Token(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Token(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<ApprovalEventResponse> getApprovalEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(APPROVAL_EVENT, transactionReceipt);
        ArrayList<ApprovalEventResponse> responses = new ArrayList<ApprovalEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ApprovalEventResponse typedResponse = new ApprovalEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.spender = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ApprovalEventResponse>() {
            @Override
            public ApprovalEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVAL_EVENT, log);
                ApprovalEventResponse typedResponse = new ApprovalEventResponse();
                typedResponse.log = log;
                typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.spender = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVAL_EVENT));
        return approvalEventFlowable(filter);
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

    public List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TransferEventResponse> transferEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, TransferEventResponse>() {
            @Override
            public TransferEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFER_EVENT, log);
                TransferEventResponse typedResponse = new TransferEventResponse();
                typedResponse.log = log;
                typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TransferEventResponse> transferEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFER_EVENT));
        return transferEventFlowable(filter);
    }

    public RemoteFunctionCall<BigInteger> allowance(String from, String to) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ALLOWANCE, 
                Arrays.<Type>asList(new Address(160, from),
                new Address(160, to)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> approve(String from, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_APPROVE, 
                Arrays.<Type>asList(new Address(160, from),
                new Uint256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> balanceOf(String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(new Address(160, account)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> chargeToken(String to, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CHARGETOKEN, 
                Arrays.<Type>asList(new Address(160, to),
                new Uint256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> decimals() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_DECIMALS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> decreaseApprove(String from, BigInteger subAmount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_DECREASEAPPROVE, 
                Arrays.<Type>asList(new Address(160, from),
                new Uint256(subAmount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> increaseApprove(String from, BigInteger addAmount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_INCREASEAPPROVE, 
                Arrays.<Type>asList(new Address(160, from),
                new Uint256(addAmount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> name() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
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

    public RemoteFunctionCall<String> symbol() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SYMBOL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> totalSupply() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOTALSUPPLY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> transfer(String to, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new Address(160, to),
                new Uint256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferFrom(String from, String to, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFERFROM, 
                Arrays.<Type>asList(new Address(160, from),
                new Address(160, to),
                new Uint256(amount)),
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

    public RemoteFunctionCall<TransactionReceipt> withdrawalToken(String from, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_WITHDRAWALTOKEN, 
                Arrays.<Type>asList(new Address(160, from),
                new Uint256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static Token load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Token(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Token load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Token(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Token load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Token(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Token load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Token(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Token> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String name_, String symbol_, String member_) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Utf8String(name_),
                new Utf8String(symbol_),
                new Address(160, member_)));
        return deployRemoteCall(Token.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<Token> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String name_, String symbol_, String member_) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Utf8String(name_),
                new Utf8String(symbol_),
                new Address(160, member_)));
        return deployRemoteCall(Token.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Token> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String name_, String symbol_, String member_) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Utf8String(name_),
                new Utf8String(symbol_),
                new Address(160, member_)));
        return deployRemoteCall(Token.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Token> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String name_, String symbol_, String member_) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Utf8String(name_),
                new Utf8String(symbol_),
                new Address(160, member_)));
        return deployRemoteCall(Token.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class ApprovalEventResponse extends BaseEventResponse {
        public String owner;

        public String spender;

        public BigInteger value;
    }

    public static class OwnershipTransferredEventResponse extends BaseEventResponse {
        public String previousOwner;

        public String newOwner;
    }

    public static class TransferEventResponse extends BaseEventResponse {
        public String from;

        public String to;

        public BigInteger value;
    }
}
