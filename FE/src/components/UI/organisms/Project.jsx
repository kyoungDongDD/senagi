import { useState, useEffect } from 'react';
import Carousel from 'nuka-carousel';
import styled from '@emotion/styled';
import { Box } from '@mui/material';
import campaignAPI from '../../../api/campaignAPI';
import Text from '../atoms/Text';
import MoreBtn from '../molecules/MoreButton';
import Card from './DonationInfoCard';

function Project() {
  const Container = styled.div`
    background: linear-gradient(#fffcf3 60%, #fff 40%);
  `;

  const [posts, setPosts] = useState([]);
  useEffect(() => {
    campaignAPI.getCampaignByType('PROJECT').then((response) => {
      const campaignAll = response.content;
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
            mt: 10,
            mr: 20,
          }}
        >
          <Box
            sx={{
              alignItems: { xs: 'center', md: 'flex-start' },
              width: 900,
            }}
          >
            <Carousel
              autoplay={true}
              wrapAround={true}
              slideListMargin={1}
              slidesToScroll={1}
              slidesToShow={1.85}
              cellAlign={'right'}
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
                  <Card
                    id={id}
                    title={title}
                    shelterName={shelterName}
                    thumbnailImageUrl={thumbnailImageUrl}
                    targetDonation={targetDonation}
                    endDate={endDate}
                    lastModifiedDate={lastModifiedDate}
                  />
                ),
              )}
            </Carousel>
          </Box>

          <Box
            sx={{
              display: 'flex',
              flexDirection: 'column',
              alignItems: { xs: 'center', md: 'flex-end' },
              width: 600,
            }}
          >
            <Box>
              <Text className="header1" text="프로젝트 후원" />
            </Box>
            <Box>
              <Text className="body2" text="관심있는 프로젝트에 후원하세요." />
            </Box>
            <Box>
              <MoreBtn />
            </Box>
          </Box>
        </Box>
      </Container>
    </>
  );
}

export default Project;
