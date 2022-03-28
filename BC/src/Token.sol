// SPDX-License-Identifier: MIT

pragma solidity >=0.7.0 <0.9.0;

import "./standard/IERC20.sol";
import "./standard/IERC20Metadata.sol";
import "./standard/Ownable.sol";
import "./Member.sol";

contract Token is IERC20, IERC20Metadata, Ownable {

    uint256 private _totalSupply;
    mapping (address => uint256) private _balances;
    mapping (address => mapping(address => uint256)) private _allowances;
    Member private _memberMgr;

    // Metadata
    string private _name;
    string private _symbol;
    uint8 private _decimals = 18;

    constructor(string memory name_, string memory symbol_, Member member_) {
        _name = name_;
        _symbol = symbol_;
        _decimals = 18;
        _memberMgr = member_;

        _mint(msg.sender, 1000000000000 * 10 ** uint256(_decimals));
    }

    // Getter
    function totalSupply() public view override returns (uint256) {
        return _totalSupply;
    }

    function name() public view override returns (string memory) {
        return _name;
    }

    function symbol() public view override returns (string memory) {
        return _symbol;
    }

    function decimals() public view override returns (uint8) {
        return _decimals;
    }

    function balanceOf(address account) public view override returns (uint256) {
        return _balances[account];
    }

    function allowance(address from, address to) public view override returns (uint256) {
        to = owner();
        return _allowances[from][to];
    }

    function _mint(address to, uint256 amount) private {
        require(to != address(0),"TokenError : _mint to the zero address");
        require(amount > 0, "TokenError : amount <= 0");
        _totalSupply += amount;
        _balances[to] += amount;
        emit Transfer(owner(), to, amount);
    }

    function _approve(address from, address to, uint256 amount) private {
        require(from != address(0), "TokenError : approve from the zero address");
        require(to != address(0), "TokenError : approve to the zero address");
        require(_balances[from] >= amount, "TokenError : amount exceeds balances");

        _allowances[from][to] = amount;
        emit Approval(from, to, amount);
    }

    function approve(address from, uint256 amount) external override returns (bool) {
        require(_memberMgr.isMember(from),"TokenError : This account is not registered member");
        require(amount > 0, "TokenError : amount <= 0");
        _approve(from, owner(), amount);
        return true;
    }

    function increaseApprove(address from, uint256 addAmount) external onlyOwner returns (bool) {
        require(addAmount > 0, "TokenError : addAmount <= 0");
        uint256 newAmount = _allowances[from][_msgSender()] + addAmount;
        require(newAmount >= _allowances[from][_msgSender()], "TokenError : Increase approve overflow exception");
        require(newAmount <= _balances[from],"TokenError : This amount exceeds balance");
        _approve(from, msg.sender, newAmount);
        return true;
    }

    function decreaseApprove(address from, uint256 subAmount) external onlyOwner returns (bool) {
        require(subAmount > 0, "TokenError : subAmount <= 0");
        uint256 newAmount = _allowances[from][_msgSender()] - subAmount;
        require(newAmount >= 0, "TokenError : allowances + subAmount < 0");
        require(newAmount <= _allowances[from][_msgSender()], "TokenError : Decrease approve underflow exception");
        _approve(from, msg.sender, newAmount);
        return true;
    }

    function _transfer(address from, address to, uint256 amount) private {
        require(_memberMgr.isTransferPossible(from, to), "TokenError : from -> to is not possible");
        require(from != address(0), "TokenError : transfer from the zero address");
        require(to != address(0), "TokenError : transfer to the zero address");
        require(amount > 0, "TokenError : amount must be greater than 0");

        uint256 fromBalance = _balances[from];
        uint256 destBalance = _balances[to] + amount;
        require(fromBalance >= amount,"TokenError : transfer amount exceeds balance");
        require(destBalance >= _balances[to], "TokenError : transfer amount overflow" );
        _balances[from] -= amount;
        _balances[to] += amount;
        emit Transfer(from, to, amount);
    }

    function transfer(address to, uint256 amount) external override returns (bool) {
        require(_memberMgr.isMember(to),"TokenError : This account is not registered member");
        _transfer(_msgSender(), to, amount);
        return true;
    }
    
    function chargeToken(address to, uint256 amount) external onlyOwner returns(bool) {
        require(_memberMgr.isMember(to),"TokenError : This account is not registered member");
        _transfer(owner(),to,amount);
        return true;
    }

    function withdrawalToken(address from, uint256 amount) external onlyOwner returns(bool) {
        require(_memberMgr.isWithdrawalPossible(from), "TokenError : This account cannot be withdrawnl");
        _transfer(from, owner(), amount);
        return true;
    }
    
    /**
    * 제 3자가 토큰 교환을 중개할 때 사용합니다.
    * C사용자가 A의 계좌에서 B의 계좌로 토큰을 송금하는 경우
    * 제 3자가 인출하기 때문에 _allowances를 이용합니다.
    */
    function transferFrom(address from, address to, uint256 amount) external override returns (bool) {
        require(_allowances[from][owner()] >= amount, "TokenError : transfer amount exceeds allowance");
        _transfer(from, to, amount);
        _approve(from, owner(), _allowances[from][owner()]-amount);
        return true;
    }
}