import { useState, useEffect } from 'react';
import Carousel from 'nuka-carousel';
import styled from '@emotion/styled';
import { Box } from '@mui/material';
import ChevronRightIcon from '@mui/icons-material/ChevronRight';
import campaignAPI from '../../../api/campaignAPI';
import Text from '../atoms/Text';
import Card from './DonationInfoCard';

function Project() {
  const StyleButton = styled.button`
    /* width: 30px; */
    /* padding: 5px; */
    border: none;
    /* border-radius: 50%; */
    background-color: white;
    opacity: 0.5;
    cursor: pointer;
  `;

  const [posts, setPosts] = useState([]);
  useEffect(() => {
    campaignAPI.getCampaignByType('PROJECT').then((response) => {
      const campaignAll = response.data.content;
      console.log(campaignAll);
      setPosts(campaignAll);
    });
  }, []);
  return (
    <>
      <Box
        sx={{
          display: 'flex',
          flexDirection: { xs: 'column', md: 'row' },
          justifyContent: 'center',

          p: 10,
        }}
      >
        <Box
          sx={{
            display: 'flex',
            flexDirection: 'column',
            alignItems: { xs: 'center', md: 'flex-start' },
          }}
        >
          <Box>
            <Text className="campaignHeader" text="프로젝트 후원" />
          </Box>
          <Box>
            <Text className="campaignDes" text="관심있는 프로젝트에 후원하세요." />
          </Box>
          <Box>
            <Carousel
              framePadding="40px"
              autoplay={true}
              wrapAround={true}
              slideListMargin={2}
              slidesToScroll={1}
              slidesToShow={2}
              renderCenterRightControls={({ nextSlide }) => (
                <StyleButton onClick={nextSlide}>
                  <ChevronRightIcon sx={{ fontSize: 40 }} />
                </StyleButton>
              )}
              renderCenterLeftControls={null}
              renderBottomCenterControls={null}
            >
              {posts.map(
                ({
                  id,
                  title,
                  shelterName,
                  targetDonation,
                  thumbnailImageUrl,
                  endDate,
                  lastModifiedDate,
                  balance,
                }) => (
                  <div key={id}>
                    <Card
                      id={id}
                      title={title}
                      shelterName={shelterName}
                      thumbnailImageUrl={thumbnailImageUrl}
                      targetDonation={targetDonation}
                      endDate={endDate}
                      lastModifiedDate={lastModifiedDate}
                      balance={balance}
                    />
                  </div>
                ),
              )}
            </Carousel>
          </Box>
        </Box>
      </Box>
    </>
  );
}

export default Project;
