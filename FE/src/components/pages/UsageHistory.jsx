
import React from 'react';
import Banner from "../UI/organisms/Banner";
import NavBar from '../UI/organisms/NavBar';
import ReceiptImage from '../UI/organisms/ReceiptImage';
import CampaignCard from '../UI/organisms/CampaignCard';


import styled from '@emotion/styled';
import { Grid } from '@mui/material';

function UsageHistory() {
  return (
    <div>
      <NavBar />
      <Banner />
      <LeyoutContainer>
        <h2>기부금 사용 내역</h2>
        <Grid container justifyContent={'space-evenly'}>
            <ReceiptImage />
            <Grid item xs={6} lg={2}>
                <CardPosition>
                  <CampaignCard />
                </CardPosition>
            </Grid>
        </Grid>
        <p className='body1'>사용금액 &nbsp;456,789원</p>
      </LeyoutContainer>
      
    </div>
  );
}

export default UsageHistory;

const CardPosition = styled.div`
  position: relative;
`
const LeyoutContainer = styled.div`
  height: 800px
`