import React, { useEffect, useState } from 'react';
import CampaignCard from './CampaignCard';
import Grid from '@mui/material/Grid';
import CampaignTable from './CampaignTable';
import { getCampaignById } from '../../../api/campaignAPI';

import styled from '@emotion/styled';

function CampaignDetail() {
  const [datas, setDatas] = useState([]);

  useEffect((id = 0) => {
    getCampaignById(1).then((response) => {
      const campaignDetail = response.content;
      setDatas(campaignDetail);
      console.log(campaignDetail);
    });
  }, []);

  return (
    <div>
      <br></br>
      <Grid container justifyContent={'center'} spacing={2}>
        <Grid item xs={1} md={1} lg={1}></Grid>
        <Grid item xs={10} md={7} lg={7}>
          <CampaginImg src={require('../../../assets/test5.jpg')} />
          <p>
            후원금은 보호소의 영수증 제출을 통해 해당 캠페인을 위해 사용한 금액만큼만 전달됩니다.
            <br></br>
            후원 잔액은 해당 보호소의 자체 캠페인으로 전달되어 사용될 수 있습니다.
          </p>
          <CampaignTable />
        </Grid>
        <Grid item xs={0} md={0} lg={1}></Grid>
        <Grid item xs={0} md={3} lg={2}>
          {/* <CardPosition> */}
          <CampaignCard />
          {/* </CardPosition> */}
        </Grid>
        <Grid item xs={1} md={1} lg={1}></Grid>
      </Grid>
    </div>
  );
}

export default CampaignDetail;

const CampaginImg = styled.img`
  width: 100%;
  height: 100%;
  border-radius: 3px;
  object-fit: fill;
`;
// const CardPosition = styled.div`
//   position: fixed;
// `;

// const StyledGrid = styled(Grid)`
//   margin: 1rem;
// `;
