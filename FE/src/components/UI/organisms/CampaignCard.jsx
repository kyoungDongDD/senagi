<<<<<<< HEAD
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
=======
import { useState, useEffect } from 'react';
import Dday from '../molecules/D-Day';
import { Card, CardContent, Typography, Grid } from '@mui/material';
import ProgressBar from '../molecules/ProgressBar';
import styled from '@emotion/styled';
import Text from '../atoms/Text';
import DonateModal from '../organisms/Modal/DonateModal';
import WithdrawModal from './Modal/WithdrawModal';
import UserButton from '../molecules/UserButton';
import storage from 'redux-persist/lib/storage';
import { useSelector } from 'react-redux';

function CampaignCard(props) {
  const {
    id,
    isEnd,
    shelterName,
    targetDonation,
    contentImageUrl,
    thumbnailImageUrl,
    title,
    type,
    lastModifiedDate,
    endDate,
    hashtags,
    balance,
    dday,
  } = props;

  const targetMoney = targetDonation ? targetDonation.toLocaleString() : targetDonation;
  const targetBalance = balance ? balance.toLocaleString() : balance;
  const [isOpen, setIsOpen] = useState();
  const handleClose = (value) => {
    setIsOpen(false);
  };

  // 현재 모금액 / 목표 금액으로 퍼센트 구하기
  const targeMoney = Number(targetDonation);
  const nowMoney = Number(balance);

  const barPer = nowMoney / targeMoney;

  //유저정보 불러오기
  const user = useSelector((state) => state.user.value.userInfo);
  const roles = user.roles[0];
  const nickName = user.nickname;
  console.log(nickName);

  return (
    <StyledCard>
      <CardContent>
        <Grid container>
          <Grid item xs={9}>
            <StyledText className="body1" text={`${targetBalance}` + '원'} />
          </Grid>
          <StyledGrid item xs={3}>
            <Dday dday={dday} />
          </StyledGrid>
        </Grid>
        {/* <Typography variant="h5" component="div">
          100,100,000 원
        </Typography> */}
        <ProgressBar percent={barPer} width="321" />
        <RightContainer>
          <Typography
            sx={{ fontSize: 16 }}
            style={{ marginTop: '10px' }}
            color="text.secondary"
            gutterBottom
          >
            {targetMoney}원
          </Typography>
          <Typography sx={{ fontSize: 14 }} color="text.secondary" gutterBottom>
            {lastModifiedDate} ~ {endDate}까지
          </Typography>
        </RightContainer>
        {roles === 'SUPPORTER' ? (
          <DonateModal
            shelterName={shelterName}
            thumbnailImageUrl={thumbnailImageUrl}
            title={title}
          />
        ) : nickName === `${shelterName}` ? (
>>>>>>> dev
          <UserButton
            type="submit"
            fullWidth
            variant="contained"
<<<<<<< HEAD
            text="캠페인 기부하기"
            size="large"
          />
          <Typography sx={{ fontSize: 14 }}>모금단체</Typography>
          <Typography>마석유기견보호소</Typography>
        </CardContent>
      </Card>
    </MediaDiv>
=======
            text="출금하기"
            size="large"
            func={() => setIsOpen(true)}
          />
        ) : (
          <></>
        )}
        <WithdrawModal isOpen={isOpen} onClose={handleClose} />
        <Typography sx={{ fontSize: 14 }}>모금단체</Typography>
        <Typography>{shelterName}</Typography>
      </CardContent>
    </StyledCard>
>>>>>>> dev
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

<<<<<<< HEAD
=======
const StyledText = styled(Text)`
  text-align: center;
  justify-content: center;
  margin: 0;
  font-size: 1.2rem;
`;

const StyledGrid = styled(Grid)`
  display: inline-grid;
  grid-template-columns: 100%;
`;

const StyledCard = styled(Card)`
  position: sticky;
  top: 50px;
  width: 280px;
  @media screen and (max-width: 900px) {
    display: None;
  }
`;

>>>>>>> dev
export default CampaignCard;
