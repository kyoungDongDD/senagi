import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import styled from '@emotion/styled';
import { useTheme } from '@mui/material/styles';
import Box from '@mui/material/Box';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';

function OwnCampaignList(props) {
  const { campaignList } = props;

  const myCampaigns = Array.from(campaignList);
  console.log(myCampaigns);

  const navigate = useNavigate();

  // const pagelink = (id) => {
  //   navigate(`/campaigninfo/${id}`, {
  //     state: {
  //       pageId: id,
  //     },
  //   });
  // };

  return (
    <div>
      <DivContainer>
        <Tile>나의 캠페인리스트</Tile>
        {myCampaigns.map((data, index) => {
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
                  image={`https://senagi.site/api/imgs/${data.thumbnailImageUrl}`}
                  alt="이미지가 없습니다.!!!!!!!"
                  // onClick={() => pagelink(data.campaignId)}
                />
                <CardContent sx={{ flex: '1 0 auto' }}>
                  <Division>
                    <div>
                      <Typography component="div" variant="h5">
                        {data.shelterName}
                      </Typography>
                      <Typography variant="subtitle1" color="text.secondary" component="div">
                        {data.title}
                      </Typography>
                      <Typography component="div" variant="h7">
                        {data.lastModifiedDate} ~ {data.endDate}
                      </Typography>
                    </div>
                    <Money>
                      {data.balance} {data.targetDonation.toLocaleString()} 세나
                    </Money>
                  </Division>
                </CardContent>
                <Box sx={{ display: 'flex', alignItems: 'center', pl: 1, pb: 1 }}></Box>
              </Box>
            </Card>
          );
        })}
      </DivContainer>
    </div>
  );
}

export default OwnCampaignList;

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
