import { useState, useEffect } from 'react';
import Carousel from 'nuka-carousel';
import styled from '@emotion/styled';
import { Box } from '@mui/material';
import ChevronRightIcon from '@mui/icons-material/ChevronRight';
import ChevronLeftIcon from '@mui/icons-material/ChevronLeft';
import campaignAPI from '../../../api/campaignAPI';
import Text from '../atoms/Text';
import Card from './DonationInfoCard';

function Project() {
  const StyleButton = styled.button`
    width: 65px;
    height: 40px;
    border: none;
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
              autoplay={false}
              wrapAround={true}
              slideListMargin={2}
              slidesToScroll={1}
              slidesToShow={3}
              renderCenterRightControls={({ nextSlide }) => (
                <StyleButton onClick={nextSlide}>
                  <ChevronRightIcon sx={{ fontSize: 40 }} />
                </StyleButton>
              )}
              renderCenterLeftControls={({ previousSlide }) => (
                <StyleButton onClick={previousSlide}>
                  <ChevronLeftIcon sx={{ fontSize: 40 }} />
                </StyleButton>
              )}
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
