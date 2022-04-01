// SPDX-License-Identifier: MIT

pragma solidity >=0.7.0 <0.9.0;

abstract contract StringUtils {

    function _strcmp(string memory a, string memory b) internal pure returns (bool) {
        return (keccak256(abi.encodePacked(a))) == (keccak256(abi.encodePacked(b)));
    }

}
