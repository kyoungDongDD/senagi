import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styled from '@emotion/styled';
import { useTheme } from '@mui/material/styles';
import Box from '@mui/material/Box';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';

function MyPageList(props) {
  const { myDonation } = props;

  const myDonaions = Array.from(myDonation);

  const navigate = useNavigate();

  const pagelink = (id) => {
    navigate(`/campaigninfo/${id}`, {
      state: {
        pageId: id,
      },
    });
  };

  return (
    <>
      <DivContainer>
        <Tile>후원 이력</Tile>
        {myDonaions.map((data, index) => {
          return (
            <Card
              sx={{ display: 'flex', flexDirection: 'column' }}
              key={index}
              style={{ marginBottom: '30px', backgroundColor: '' }}
            >
              <Box sx={{ display: 'flex', flexDirection: 'row' }}>
                <CardMedia
                  component="img"
                  sx={{ width: 151 }}
                  image={`https://j6b105.p.ssafy.io/api/imgs/${data.thumbnailImagUrl}`}
                  alt="이미지가 없습니다.!!!!!!!"
                  onClick={() => pagelink(data.campaignId)}
                />
                <CardContent sx={{ flex: '1 0 auto' }}>
                  <Division>
                    <div>
                      <Typography component="div" variant="h5">
                        {data.shelterName}
                      </Typography>
                      <Typography variant="subtitle1" color="text.secondary" component="div">
                        {data.campaignTitle}
                      </Typography>
                      <Typography component="div" variant="h7">
                        {data.donateDate}
                      </Typography>
                    </div>
                    <Money>{data.amount.toLocaleString()} 세나</Money>
                  </Division>
                </CardContent>
                <Box sx={{ display: 'flex', alignItems: 'center', pl: 1, pb: 1 }}></Box>
              </Box>
            </Card>
          );
        })}
      </DivContainer>
    </>
  );
}

export default MyPageList;

const DivContainer = styled.div`
  margin: 50px;
`;

const Tile = styled.p`
  margin: 50px;
  font-family: 'GB';
  font-size: 34px;
  text-align: center;
`;

const Money = styled.p`
  float: right;
  font-family: 'GM';
  font-size: 24px;
`;

const Division = styled.div`
  display: flex;
  justify-content: space-between;
`;
