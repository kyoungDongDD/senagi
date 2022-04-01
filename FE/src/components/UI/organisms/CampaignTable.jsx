import { useEffect, useState } from 'react';
import '../../../styles/CampaignTable.css';
import UserButton from '../molecules/UserButton';
import Dday from '../molecules/D-Day';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import ProgressBar from '../molecules/ProgressBar';
import styled from '@emotion/styled';

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
