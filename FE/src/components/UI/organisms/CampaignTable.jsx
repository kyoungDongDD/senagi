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
  const { id, isEnd, shelterName, targetDonation, endDate, account, type, registDate, hashtags } =
    props;

  const [width, setwidth] = useState(0);
  const divWidth = useRef('');

  useEffect(() => {
    const nowWidth = divWidth.current.getBoundingClientRect();
    setwidth(nowWidth.width);
    console.log(nowWidth.width);
  }, [width]);

  return (
    <div>
      <p>캠페인 및 보호소 정보</p>
      <table className="myTable headerV">
        <tr>
          <td>사업기간</td>
          <td>
            {registDate} ~ {endDate}
          </td>
        </tr>
        <tr>
          <td>보호소</td>
          <td>{shelterName}</td>
        </tr>
        <tr>
          <td>태그</td>
          <td>
            <button>for문으로 태그 돌리기</button>
          </td>
        </tr>
      </table>
      <Card sx={{ maxWidth: 900, minWidth: 321 }} style={{ position: 'relative' }}>
        <CardContent>
          <Dday dday="15" />
          <Typography variant="h5" component="div">
            {targetDonation}
          </Typography>
          <div ref={divWidth}>
            <ProgressBar id="size" percent="0.5" width={width} />
          </div>
          <RightContainer>
            <Typography
              sx={{ fontSize: 16 }}
              style={{ marginTop: '10px' }}
              color="text.secondary"
              gutterBottom
            >
              {account}
            </Typography>
          </RightContainer>
        </CardContent>
      </Card>

      <UserButton type="submit" fullWidth variant="contained" text="캠페인 기부하기" size="large" />
    </div>
  );
}

export default CampaignTable;

const RightContainer = styled.div`
  width: 100%;
  display: block;
  text-align: right;
`;
