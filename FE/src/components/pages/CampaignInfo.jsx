import { useEffect, useState } from 'react';
import styled from '@emotion/styled';
import CampaignDetail from '../UI/organisms/CampaignDetail';
import UsageHistory from '../UI/organisms/UsageHistory';

function CampaignInfo() {
  const [viewCalendar, setViewCalendar] = useState(true);

  const [currentClick, setCurrentClick] = useState(null);
  const [prevClick, setPrevClick] = useState(null);

  const GetClikc = (e) => {
    setCurrentClick(e.target.id);
  };

  useEffect(
    (e) => {
      if (currentClick !== null) {
        let current = document.getElementById(currentClick);
        current.style.color = 'black';
        current.style.borderBottom = '2px solid';
        current.style.borderBottomColor = '#F4BA3499';
      }

      if (prevClick !== null) {
        let prev = document.getElementById(prevClick);
        prev.style.color = '#bebcbc';
        prev.style.borderBottom = 'none';
      }
      setPrevClick(currentClick);
    },
    [currentClick],
  );

  return (
    <div>
      <BannerImg src={require('../../assets/test1.jpg')} />
      <BtnContainer>
        <div></div>
        <CategoryBox
          id="case1"
          onClick={(e) => {
            setViewCalendar(!false);
            GetClikc(e);
          }}
        >
          &nbsp;소개&nbsp;
        </CategoryBox>
        <CategoryBox2
          id="case2"
          onClick={(e) => {
            setViewCalendar(!true);
            GetClikc(e);
          }}
        >
          &nbsp;사용 내역&nbsp;
        </CategoryBox2>
        <div></div>
      </BtnContainer>
      {viewCalendar ? <CampaignDetail /> : <UsageHistory />}
    </div>
  );
}

export default CampaignInfo;

const BannerImg = styled.img`
  display: flex;
  width: 100%;
  height: 350px;
`;

const BtnContainer = styled.div`
  width: 99.85%;
  height: 50px;
  display: flex;
  justify-content: space-evenly;
  text-align: center;
  align-items: center;
  border: 1px solid;
`;

const CategoryBox = styled.div`
  cursor: pointer;
  color: black;
  border-bottom: 2px solid;
  border-bottom-color: #f4ba3499;
`;

const CategoryBox2 = styled.div`
  cursor: pointer;
  color: #bebcbc;
  border-bottom: none;
`;
