import { useState, useEffect } from 'react';
import paymentAPI from '../../api/paymentAPI';
import styled from '@emotion/styled';

function MyPage() {
  const [myDonation, setMyDonation] = useState('');

  useEffect(() => {
    paymentAPI.mydonation().then((response) => {
      const myDonation = response.data;
      console.log(myDonation);
      setMyDonation(myDonation);
    });
  }, []);

  console.log(...myDonation);

  const dontionList = () => {
    const result = [];
    for (let i = 0; i < myDonation.length; i++) {
      result.push(
        <div key={i}>
          <span>{myDonation[i].shelterName}</span>
          <span>{myDonation[i].amount}</span>
        </div>,
      );
    }
    return result;
  };

  return (
    <div>
      <DivContainer>
        <Tile>후원 이력</Tile>
        {dontionList()}
      </DivContainer>
    </div>
  );
}

export default MyPage;

const DivContainer = styled.div`
  margin: 50px;
`;

const Tile = styled.p`
  margin: 50px;
  font-family: 'GB';
  font-size: 34px;
`;
