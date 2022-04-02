import styled from '@emotion/styled';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
// import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';

import ProgressBar from './../molecules/ProgressBar';
import Dday from '../molecules/D-Day';

export default function ImgCard(props) {
  const { id, isEnd, shelterName, targetDonation, thumbnailImageUrl, title, type } = props;

  return (
    //max min 똑같은 이유, ProgressBar에 영향을 안주기위해 고정값으로 주려고..
    <Card sx={{ maxWidth: 345, minWidth: 345 }} style={{ position: 'relative', margin: '15px' }}>
      <Dday dday={'15'} />
      <CardMedia
        id={id}
        component="img"
        alt="green iguana"
        height="200"
        image={require('../../../assets/test1.jpg')}
        // image={require({ thumbnailImageUrl })}
        object-fit={'cover'}
      />
      <CardContent>
        <Typography gutterBottom variant="h5" component="div">
          {title}
        </Typography>
        <Typography variant="body2" color="text.secondary">
          {shelterName}
        </Typography>
        <ProgressBar percent="0.5" width="313" />
        <Money className="body1">{targetDonation.toLocaleString()} 원</Money>
        <Progress className="body1">50%</Progress>
      </CardContent>
    </Card>
  );
}

const Money = styled.div`
  margin-top: 15px;
  display: inline-block;
`;

const Progress = styled.div`
  margin-top: 15px;
  float: right;
`;
