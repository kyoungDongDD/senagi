// SPDX-License-Identifier: MIT

pragma solidity >=0.7.0 <0.9.0;

import "./standard/Ownable.sol";
import "./utils/StringUtils.sol";


contract Member is Ownable, StringUtils {

    enum MemberTypes {
        None, Supporter, Shelter, ProjectCampaign, ShelterCampaign
    }

    mapping(address => MemberTypes) _memberTypes;

    function _typeOf(address account) internal view returns(MemberTypes) {
        return _memberTypes[account];
    }

    function _stringToMemberType(string memory memberType) internal pure returns(MemberTypes) {
        if(_strcmp(memberType, "ProjectCampaign"))
            return MemberTypes.ProjectCampaign;
        else if(_strcmp(memberType, "ShelterCampaign"))
            return MemberTypes.ShelterCampaign;
        else if(_strcmp(memberType, "Shelter"))
            return MemberTypes.Shelter;  
        else if(_strcmp(memberType, "Supporter"))
            return MemberTypes.Supporter;
        else 
            revert();
    }

    function newMember(address account, MemberTypes memberType) onlyOwner external returns(bool) {
        require(_typeOf(account) == MemberTypes.None,"Member error : This address already exists");
        _memberTypes[account] = memberType;
        return true;
    }

    /** @dev from, to의 타입에 따른 거래 가능 여부를 반환한다.
        owner : 모두 가능
        Supporter(후원자) : 캠페인(프로젝트, 보호소)
        ProjectCampaign : 잔액 환급 (보호소 캠페인), 출금(보호소)    
        Shelter : 불가능 (출금 전용 계좌)
    */
    function isTransferPossible(address from, address to) external view returns(bool) {
        if(from == owner())
            return true;
            
        if(_typeOf(from) == MemberTypes.Supporter) {
            if(_typeOf(to) == MemberTypes.ShelterCampaign 
                || _typeOf(to) == MemberTypes.ProjectCampaign
                || to == owner())
                return true;
        }
        else if(_typeOf(from) == MemberTypes.ProjectCampaign) {
            if(_typeOf(to) == MemberTypes.ShelterCampaign 
                || _typeOf(to) == MemberTypes.Shelter)
                return true;
        } else if (_typeOf(from) == MemberTypes.ShelterCampaign) {
            if(_typeOf(to) == MemberTypes.Shelter)
                return true;
        } else if(_typeOf(from) == MemberTypes.Shelter && to == owner()) {
            return true;
        }
        return false;
    }

    function isMember(address addr) external view returns(bool){
        return _typeOf(addr) != MemberTypes.None;
    }

    function isWithdrawalPossible(address addr) external view returns(bool) {
        return _typeOf(addr) == MemberTypes.Supporter || _typeOf(addr) == MemberTypes.Shelter;
    }

    function isSupporter(address addr) external view returns(bool) {
        return _typeOf(addr) == MemberTypes.Supporter;
    }

    function isShelter(address addr) external view returns(bool) {
        return _typeOf(addr) == MemberTypes.Shelter;
    }

    function isShelterTerCampaign(address addr) external view returns(bool) {
        return _typeOf(addr) == MemberTypes.ShelterCampaign;
    }
}