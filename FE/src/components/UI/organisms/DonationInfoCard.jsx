// Text, ProgressBar
// D-Day, Button
import * as React from 'react';
import styled from '@emotion/styled';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
// import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';

import ProgressBar from "./../molecules/ProgressBar";
import Dday from '../molecules/D-Day';

export default function ImgCard() {
  return (
    <Card sx={{ maxWidth: 345 }} style={{ position: 'relative', margin: '15px'}}>
      <Dday />
      <CardMedia
        component="img"
        alt="green iguana"
        height="200"
        image={ require('../../../assets/test1.jpg')}
        object-fit= {'cover'}
      />
      <CardContent>
        <Typography gutterBottom variant="h5" component="div">
          경동이를 도와주세요
        </Typography>
        <Typography variant="body2" color="text.secondary">
          마석유기견보호소
        </Typography>
        <ProgressBar percent="0.5" />
        <Money className='body1'>1,500,000원</Money>
        <Progress className='body1'>50%</Progress>
      </CardContent>
    </Card>
  );
}


const Money = styled.div`
  margin-top: 15px;
  display: inline-block
`

const Progress = styled.div`
  margin-top: 15px;
  float: right;
`

