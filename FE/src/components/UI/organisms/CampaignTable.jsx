<<<<<<< HEAD
import { useEffect, useState } from 'react';
=======
import { useEffect, useState, useRef, useLayoutEffect } from 'react';
>>>>>>> dev
import '../../../styles/CampaignTable.css';
import UserButton from '../molecules/UserButton';
import Dday from '../molecules/D-Day';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import ProgressBar from '../molecules/ProgressBar';
import styled from '@emotion/styled';
<<<<<<< HEAD

function CampaignTable() {
  const [width, setwidth] = useState(0);

  const resizeWindow = () => {
    setwidth(window.innerWidth);
  };

  useEffect(() => {
    console.log(width);
  }, [width]);

  return (
    <div>
      <p>캠페인 및 보호소 정보</p>
      <table className="myTable headerV">
        <tr>
          <td>사업기간</td>
          <td>2022.03.05 ~ 2022.12.31</td>
        </tr>
        <tr>
          <td>보호소</td>
          <td>마석유기견보호소</td>
        </tr>
        <tr>
          <td>태그</td>
          <td>
            <button>태그버튼 만들기!</button>
          </td>
        </tr>
      </table>
      <Card sx={{ maxWidth: 900, minWidth: 321 }} style={{ position: 'relative' }}>
        <CardContent>
          <Dday dday="15" />
          <Typography variant="h5" component="div">
            100,100,000 원
          </Typography>
          <ProgressBar id="size" percent="0.5" width={350} />
          <RightContainer>
            <Typography
              sx={{ fontSize: 16 }}
              style={{ marginTop: '10px' }}
              color="text.secondary"
              gutterBottom
            >
              50,000,000원
=======
import DonateModal from './Modal/DonateModal';

function CampaignTable(props) {
  const {
    id,
    isEnd,
    shelterName,
    targetDonation,
    endDate,
    type,
    lastModifiedDate,
    hashtags,
    balance,
    dday,
    title,
    thumbnailImageUrl,
  } = props;

  const [width, setwidth] = useState(0);
  const divWidth = useRef('');

  useEffect(() => {
    const nowWidth = divWidth.current.getBoundingClientRect();
    setwidth(nowWidth.width);
  }, [width]);

  // 현재 모금액 / 목표 금액으로 퍼센트 구하기
  const targeMoney = Number(targetDonation);
  const nowMoney = Number(balance);

  const barPer = nowMoney / targeMoney;

  return (
    <>
      <p>캠페인 및 보호소 정보</p>
      <table className="myTable headerV">
        <tbody>
          <tr>
            <td>사업기간</td>
            <td>
              {lastModifiedDate} ~ {endDate}
            </td>
          </tr>
          <tr>
            <td>보호소</td>
            <td>{shelterName}</td>
          </tr>
          <tr>
            <td>태그</td>
            <td>
              {hashtags &&
                hashtags.map((item, index) => {
                  return <TagButton key={index}>#{item}</TagButton>;
                })}
              {/* 버튼 스타일 변경해야함 */}
            </td>
          </tr>
        </tbody>
      </table>
      <Card sx={{ minWidth: 321 }} style={{ position: 'relative' }}>
        <CardContent>
          <Dday dday={dday} />
          <Typography variant="h5" component="div">
            {nowMoney.toLocaleString()}원
          </Typography>
          <div ref={divWidth}>
            <ProgressBar id="size" percent={barPer} width={width} />
          </div>
          <RightContainer>
            <Typography
              sx={{ fontSize: 16 }}
              style={{ marginTop: '10px', marginRight: '10px' }}
              color="text.secondary"
              gutterBottom
            >
              {targeMoney.toLocaleString()}원
>>>>>>> dev
            </Typography>
          </RightContainer>
        </CardContent>
      </Card>
<<<<<<< HEAD

      <UserButton type="submit" fullWidth variant="contained" text="캠페인 기부하기" size="large" />
    </div>
=======
      <DonateModal shelterName={shelterName} thumbnailImageUrl={thumbnailImageUrl} title={title} />
    </>
>>>>>>> dev
  );
}

export default CampaignTable;

const RightContainer = styled.div`
  width: 100%;
  display: block;
  text-align: right;
`;
<<<<<<< HEAD
=======

const TagButton = styled.button`
  margin: 0px 4px 0px 4px;
  padding: 0.5rem 1rem;
  font-family: 'GL', sans-serif;
  font-size: 0.8rem;
  font-weight: 300;
  text-align: center;
  text-decoration: none;
  color: grey;
  display: inline-block;
  width: auto;
  border: none;
  border-radius: 4px;
  box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1), 0 2px 4px -1px rgba(0, 0, 0, 0.06);
  cursor: pointer;
  transition: 0.5s;
  &:hover {
    background: var(--button-hover-bg-color);
    outline: 0;
  }
  &:disabled {
    opacity: 0.5;
  }
`;

// const Max = styled.div`
//   max-width: 900px;
// `;
>>>>>>> dev
