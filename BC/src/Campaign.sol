// SPDX-License-Identifier: MIT

pragma solidity >=0.7.0 <0.9.0;

import "./Ownable.sol";
import "./Member.sol";
import "./Token.sol";

contract Campaign is Ownable{

    Member private _memberMgr;
    Token private _tokenMgr;

    uint256 private _targetAmount;
    uint256 private _deadLine;
    bool private _isEnded;

    /*
    deadline : unix timestamp
    */
    constructor(uint256 targetAmount_, uint256 deadLine_, Member member_, Token token_) {
        _memberMgr = member_;
        _tokenMgr = token_;

        _targetAmount = targetAmount_;
        _deadLine = deadLine_;
        _isEnded = false;
    }

    function _balanceOf() internal view returns(uint256) {
        return _tokenMgr.balanceOf(address(this));
    }

    function _donatePossible() internal returns(bool){
        if(_isEnded == true)
            return false;
        if(_targetAmount <= _balanceOf() || _deadLine <= block.timestamp) {
            _isEnded = true;
            return false;
        }
        return true;        
    }

    // External

    // 후원 기능
    // from : 후원자 account, amount : 금액, msg.sender : owner
    function donate(address from, uint256 amount) external onlyOwner returns(bool) {
        require(_donatePossible(),"CampaignError : This campaign already end");
        require(_memberMgr.isSupporter(from), "CampaignError : Only supporters can donate");
        uint256 possibleAmount = _targetAmount - _balanceOf();
        if(amount >= possibleAmount)
            amount = possibleAmount;
        _tokenMgr.approve(from,amount);
        _tokenMgr.transferFrom(from,address(this),amount);
        if(_balanceOf() == _targetAmount) {
            _isEnded = true;
            return true;
        }
        return false;        
    }

    // to : 받을 계좌, amount : 금액, msg.sender : owner
    function withdrawal(address to, uint256 amount) external onlyOwner returns(bool){
        require(!_donatePossible(),"Campaign error : This campaign not yet end");
        require(_memberMgr.isShelter(to), "CampaignError : Only shelters can withdraw money");
        _tokenMgr.approve(address(this),amount);
        _tokenMgr.transferFrom(address(this),to,amount);
        return true;
    }

    // to : 받을 계좌
    function close(address to) external onlyOwner  {
        require(!_donatePossible(),"Campaign error : This campaign not yet end");
        require(_memberMgr.isShelterCampaign(to),"Campaign error : Dest account only shelter campaign account");
        uint256 balance = _balanceOf();
        _tokenMgr.approve(address(this),balance);
        _tokenMgr.transferFrom(address(this),to,balance);

    }

    // get function
    function isEnded() external view returns (bool) {
        return _isEnded;
    }

    function deadLine() external view returns (uint256) {
        return _deadLine;
    }

    function targetAmount() external view returns (uint256) {
        return _targetAmount;
    }
}