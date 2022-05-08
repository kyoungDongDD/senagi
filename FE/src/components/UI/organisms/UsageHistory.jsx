<<<<<<< HEAD

import React from 'react';
import ReceiptImage from './ReceiptImage';
import CampaignCard from './CampaignCard';

=======
import { useState, useEffect } from 'react';
import ReceiptImage from './ReceiptImage';
import CampaignCard from './CampaignCard';
import PaymentAPI from '../../../api/paymentAPI';
>>>>>>> dev

import styled from '@emotion/styled';
import { Grid } from '@mui/material';

<<<<<<< HEAD
function UsageHistory() {
  return (
    <div>
      <LeyoutContainer>
        <Grid container justifyContent={'space-evenly'}>
          <ReceiptImage />
            <Grid item xs={6} lg={2}>
                <CardPosition>
                  <CampaignCard />
                </CardPosition>
            </Grid>
=======
function UsageHistory(props) {
  const { targetDonation, lastModifiedDate, endDate, shelterName, balance, pageId, dday } = props;

  const [Posts, setPosts] = useState('');

  useEffect(() => {
    PaymentAPI.getWithdraw(pageId).then((response) => {
      const campaign = response.data;
      setPosts(campaign);
    });
  }, []);

  const receipImages = Posts.receiptImageUrl;

  // amountAll 값이 없을 시 그대로 출력해서 오류 방지
  const amount = Posts.amountAll ? Posts.amountAll.toLocaleString() : Posts.amountAll;

  return (
    <div>
      <LeyoutContainer>
        <Grid container justifyContent={'space-evenly'} marginTop={'30px'}>
          <Grid>
            <ReceiptImage receipImages={receipImages} />
            {amount ? (
              <Usage>사용금액 : &nbsp; {amount}원</Usage>
            ) : (
              <NoUsage>사용 내역이 없습니다.</NoUsage>
            )}
          </Grid>
          <Grid>
            <CardPosition>
              <CampaignCard
                targetDonation={targetDonation}
                lastModifiedDate={lastModifiedDate}
                endDate={endDate}
                shelterName={shelterName}
                balance={balance}
                dday={dday}
              />
            </CardPosition>
          </Grid>
>>>>>>> dev
        </Grid>
      </LeyoutContainer>
    </div>
  );
}

export default UsageHistory;

const CardPosition = styled.div`
  position: relative;
  margin-top: 35px;
<<<<<<< HEAD
  @media screen and ( max-width: 1200px ) {
    display: None;
  }
`

const LeyoutContainer = styled.div`
  height: 700px;
`


=======
  @media screen and (max-width: 1200px) {
    display: None;
  }
`;

const LeyoutContainer = styled.div`
  height: 700px;
`;

const Usage = styled.p`
  text-align: center;
  font-family: 'GM';
  font-size: 32px;
  margin-top: 0px;
`;

const NoUsage = styled.p`
  text-align: center;
  font-family: 'GM';
  font-size: 32px;
`;
>>>>>>> dev
