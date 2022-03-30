import Dday from '../molecules/D-Day';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import UserButton from '../molecules/UserButton';
import ProgressBar from '../molecules/ProgressBar';
import styled from '@emotion/styled';

function CampaignCard() {
  return (
    //max min 똑같은 이유, ProgressBar에 영향을 안주기위해 고정값으로 주려고..
    <MediaDiv>
      <Card sx={{ maxWidth: 321, minWidth: 321 }} style={{ position: 'relative' }}>
        <CardContent>
          <Dday dday="15" />
          <Typography variant="h5" component="div">
            100,100,000 원
          </Typography>
          <ProgressBar percent="0.5" width="321" />
          <RightContainer>
            <Typography
              sx={{ fontSize: 16 }}
              style={{ marginTop: '10px' }}
              color="text.secondary"
              gutterBottom
            >
              50,000,000원
            </Typography>
            <Typography sx={{ fontSize: 14 }} color="text.secondary" gutterBottom>
              2022.03.05~2022.04.30까지
            </Typography>
          </RightContainer>
          <UserButton
            type="submit"
            fullWidth
            variant="contained"
            text="캠페인 기부하기"
            size="large"
          />
          <Typography sx={{ fontSize: 14 }}>모금단체</Typography>
          <Typography>마석유기견보호소</Typography>
        </CardContent>
      </Card>
    </MediaDiv>
  );
}

const RightContainer = styled.div`
  width: 100%;
  display: block;
  text-align: right;
`;

const MediaDiv = styled.div`
  @media screen and (max-width: 900px) {
    display: None;
  }
`;

export default CampaignCard;
