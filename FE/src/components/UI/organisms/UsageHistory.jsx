import React from 'react';
import ReceiptImage from './ReceiptImage';
import CampaignCard from './CampaignCard';

import styled from '@emotion/styled';
import { Grid } from '@mui/material';

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
        </Grid>
      </LeyoutContainer>
    </div>
  );
}

export default UsageHistory;

const CardPosition = styled.div`
  position: relative;
  margin-top: 35px;
  @media screen and (max-width: 1200px) {
    display: None;
  }
`;

const LeyoutContainer = styled.div`
  height: 700px;
`;
