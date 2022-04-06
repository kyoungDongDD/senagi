import styled from '@emotion/styled';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
// import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';

import ProgressBar from './../molecules/ProgressBar';
import Dday from '../molecules/D-Day';

import { useNavigate } from 'react-router-dom';

function ImgCard(props) {
  const {
    id,
    isEnd,
    shelterName,
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

  return (
    //max min 똑같은 이유, ProgressBar에 영향을 안주기위해 고정값으로 주려고..
    <Card sx={{ maxWidth: 345, minWidth: 345 }} style={{ position: 'relative', margin: '15px' }}>
      <Dday dday={dateDays} />
      <CardMedia
        id={id}
        component="img"
        alt="green iguana"
        height="200"
        image={`https://j6b105.p.ssafy.io/api/imgs/${thumbnailImageUrl}`}
        // image={require({ thumbnailImageUrl })}
        object-fit={'cover'}
        onClick={openDetail}
        // onmouseover={} 커서 옵션이 없는듯함.. 호버시 반응이 있게 만들어 줘야할듯
      />
      <CardContent>
        <Typography gutterBottom variant="h5" component="div">
          {title}
        </Typography>
        <Typography variant="body2" color="text.secondary">
          {shelterName}
        </Typography>
        <ProgressBar percent={barPer} width="313" />
        <Money className="body1">{targetMoney} 원</Money>
        <Progress className="body1">50%</Progress>
      </CardContent>
    </Card>
  );
}

export default ImgCard;

const Money = styled.div`
  margin-top: 15px;
  display: inline-block;
`;

const Progress = styled.div`
  margin-top: 15px;
  float: right;
`;
