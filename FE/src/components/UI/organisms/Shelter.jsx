import { useState, useEffect } from 'react';
import Carousel from 'nuka-carousel';
import styled from '@emotion/styled';
import { Box } from '@mui/material';
import campaignAPI from '../../../api/campaignAPI';
import Text from '../atoms/Text';
import MoreBtn from '../molecules/MoreButton';
import Card from './DonationInfoCard';

function Shelter() {
  const Container = styled.div`
    background: linear-gradient(#fff 70%, #fffcf3 30%);
  `;
  const [posts, setPosts] = useState([]);
  useEffect(() => {
    campaignAPI.getCampaignByType('SHELTER').then((response) => {
      const campaignAll = response.data.content;
      console.log(campaignAll);
      setPosts(campaignAll);
    });
  }, []);
  return (
    <>
      <Container>
        <Box
          sx={{
            display: 'flex',
            flexDirection: { xs: 'column', md: 'row' },
            justifyContent: 'right',
            mt: 30,
            ml: 20,
            mr: 20,
            mb: 20,
          }}
        >
          <Box
            sx={{
              display: 'flex',
              flexDirection: 'column',
              alignItems: { xs: 'center', md: 'flex-start' },
              width: 900,
            }}
          >
            <Box>
              <Text className="header1" text="보호소 후원" />
            </Box>
            <Box>
              <Text className="body2" text="관심있는 보호소에 후원하세요." />
            </Box>
            <Box>
              <MoreBtn />
            </Box>
          </Box>
          <Box
            sx={{
              alignItems: { xs: 'center', md: 'flex-start' },
              width: 500,
            }}
          >
            <Carousel
              autoplay={true}
              wrapAround={true}
              slideListMargin={0}
              slidesToScroll={1}
              slidesToShow={1}
              renderCenterLeftControls={null}
              renderCenterRightControls={null}
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
                    />
                  </div>
                ),
              )}
            </Carousel>
          </Box>
        </Box>
      </Container>
    </>
  );
}

export default Shelter;
