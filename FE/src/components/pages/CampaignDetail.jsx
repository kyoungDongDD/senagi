import React from 'react';
import Banner from "../UI/organisms/Banner";
import NavBar from '../UI/organisms/NavBar';
import CampaignCard from '../UI/organisms/CampaignCard';
import Grid from '@mui/material/Grid';
import CampaignTable from '../UI/organisms/CampaignTable';

import styled from "@emotion/styled";


function CampaignDetail() {
  return (
    <div>
      <NavBar />
      <Banner />
      <br></br>
        <Grid container justifyContent={'space-evenly'}>
        <Grid item xs={1}></Grid>
          <Grid item xs={12} md={6}>
            <CampaginImg src={ require('../../assets/test5.jpg')} />
            <p
            >후원금은 보호소의 영수증 제출을 
              통해 해당 캠페인을 위해 사용한 
              금액만큼만 전달됩니다.
              <br></br>
              후원 잔액은 해당 보호소의 자체 캠페인으로 전달되어 사용될 수 있습니다.
            </p>
            <CampaignTable />
          </Grid>
          <Grid item xs={6} md={2}>
            <CardPosition>
              <CampaignCard />
            </CardPosition>
          </Grid>
           <Grid item xs={1}></Grid>
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
`
const CardPosition = styled.div`
  position: fixed;
`