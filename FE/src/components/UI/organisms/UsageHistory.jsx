import React from 'react';
import ReceiptImage from './ReceiptImage';
import CampaignCard from './CampaignCard';

import styled from '@emotion/styled';
import { Grid } from '@mui/material';

function UsageHistory(props) {
  const { targetDonation, lastModifiedDate, endDate, shelterName, balance, dday } = props;

  return (
    <div>
      <LeyoutContainer>
        <Grid container justifyContent={'space-evenly'} marginTop={'30px'}>
          <Grid>
            <ReceiptImage />
            <p>사용금액 456,789원</p>
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
