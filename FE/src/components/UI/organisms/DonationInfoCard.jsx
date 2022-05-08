import styled from '@emotion/styled';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
<<<<<<< HEAD
=======
import { ThemeProvider, createMuiTheme, CardActionArea } from '@mui/material';
>>>>>>> dev
// import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';

import ProgressBar from './../molecules/ProgressBar';
import Dday from '../molecules/D-Day';

<<<<<<< HEAD
export default function ImgCard() {
  return (
    //max min 똑같은 이유, ProgressBar에 영향을 안주기위해 고정값으로 주려고..
    <Card sx={{ maxWidth: 345, minWidth: 345 }} style={{ position: 'relative', margin: '15px' }}>
      <Dday dday={'15'} />
      <CardMedia
        component="img"
        alt="green iguana"
        height="200"
        image={require('../../../assets/test1.jpg')}
        object-fit={'cover'}
      />
      <CardContent>
        <Typography gutterBottom variant="h5" component="div">
          경동이를 도와주세요
        </Typography>
        <Typography variant="body2" color="text.secondary">
          마석유기견보호소
        </Typography>
        <ProgressBar percent="0.5" width="313" />
        <Money className="body1">1,500,000원</Money>
        <Progress className="body1">50%</Progress>
      </CardContent>
    </Card>
  );
}

=======
import { useNavigate } from 'react-router-dom';

function ImgCard(props) {
  const {
    id,
    isEnd,
    targetDonation,
    thumbnailImageUrl,
    title,
    type,
    endDate,
    balance,
    lastModifiedDate,
  } = props;

  const navigate = useNavigate();

  const openDetail = (e) => {
    navigate(`/campaigninfo/${id}`, {
      state: {
        pageId: id,
      },
    });
  };

  const theme = createMuiTheme({
    palette: {
      type: 'dark',
    },
  });

  // dday 계산
  const date1 = new Date(endDate);
  const date2 = new Date(lastModifiedDate);

  const diffDate = date1.getTime() - date2.getTime();

  const dateDays = Math.abs(diffDate / (1000 * 3600 * 24));

  // targetDonation 값이 없을 시 그대로 출력해서 오류 방지
  const targetMoney = targetDonation ? targetDonation.toLocaleString() : targetDonation;

  // 현재 모금액 / 목표 금액으로 퍼센트 구하기
  const targeMoney = Number(targetDonation);
  const nowMoney = Number(balance);

  const barPer = nowMoney / targeMoney;

  const percent = Math.floor(barPer * 100);

  return (
    <ThemeProvider theme={theme}>
      <Card
        sx={{ width: 330 }}
        style={{
          // position: 'relative',
          margin: '15px',
        }}
      >
        <CardActionArea>
          <CardMedia
            id={id}
            component="img"
            alt="green iguana"
            image={`https://senagi.site/api/imgs/${thumbnailImageUrl}`}
            // image={require({ thumbnailImageUrl })}
            object-fit={'cover'}
            onClick={openDetail}
          />
          <CardContent>
            <Dday dday={dateDays} />
            <Typography sx={{ mt: 1, mb: 2, height: 60 }}>
              <div className="campaignTitle">{title}</div>
            </Typography>
            <ProgressBar percent={barPer} width="300" />
            <Money className="cardBottom">{targetMoney} 원</Money>
            <Progress className="cardBottom">{percent}%</Progress>
          </CardContent>
        </CardActionArea>
      </Card>
    </ThemeProvider>
  );
}

export default ImgCard;

>>>>>>> dev
const Money = styled.div`
  margin-top: 15px;
  display: inline-block;
`;

const Progress = styled.div`
  margin-top: 15px;
  float: right;
`;
