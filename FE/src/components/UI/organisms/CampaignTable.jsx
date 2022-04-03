import { useEffect, useState, useRef, useLayoutEffect } from 'react';
import '../../../styles/CampaignTable.css';
import UserButton from '../molecules/UserButton';
import Dday from '../molecules/D-Day';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import ProgressBar from '../molecules/ProgressBar';
import styled from '@emotion/styled';

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
                  return <button key={index}>{item}</button>;
                })}
              {/* 버튼 스타일 변경해야함 */}
            </td>
          </tr>
        </tbody>
      </table>
      <Card sx={{ maxWidth: 900, minWidth: 321 }} style={{ position: 'relative' }}>
        <CardContent>
          <Dday dday={dday} />
          <Typography variant="h5" component="div">
            {targeMoney.toLocaleString()}원
          </Typography>
          <div ref={divWidth}>
            <ProgressBar id="size" percent={barPer} width={width} />
          </div>
          <RightContainer>
            <Typography
              sx={{ fontSize: 16 }}
              style={{ marginTop: '10px' }}
              color="text.secondary"
              gutterBottom
            >
              {nowMoney.toLocaleString()}원
            </Typography>
          </RightContainer>
        </CardContent>
      </Card>

      <UserButton type="submit" fullWidth variant="contained" text="캠페인 기부하기" size="large" />
    </>
  );
}

export default CampaignTable;

const RightContainer = styled.div`
  width: 100%;
  display: block;
  text-align: right;
`;
