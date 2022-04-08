<<<<<<< HEAD
import Carousel from 'nuka-carousel';
import styled from '@emotion/styled';
import { Grid, Box } from '@mui/material';
import Text from '../atoms/Text';
import MoreBtn from '../molecules/MoreButton';
import Card from './DonationInfoCard';

function Project() {
  const Container = styled.div`
    background: linear-gradient(#fffcf3 60%, #fff 40%);
  `;
  const DotsContainer = styled.div`
    display: flex;
    flex-direction: row;
  `;

  const DotItem = styled.span`
    cursor: pointer;
    width: 12px;
    height: 12px;
    background: ${({ active }) => (active ? 'gray' : 'black')};
    margin-bottom: 10px;
    border-radius: 100px;
  `;
  return (
    <>
      <Container>
        <Box>
          <Grid container direction="row" spacing={2}>
            <Grid item xs>
              <Carousel
                autoplay={true}
                wrapAround={true}
                slideListMargin={1}
                slidesToScroll={1}
                slidesToShow={1.75}
                cellAlign={'right'}
                renderCenterLeftControls={null}
                renderCenterRightControls={null}
                renderBottomCenterControls={({ slideCount, currentSlide }) => (
                  <DotsContainer>
                    {Array.from(Array(slideCount).keys()).map((index) => {
                      return (
                        <DotItem
                          active={currentSlide === index}
                          key={`${index}_carousel_${Math.random()}`}
                        />
                      );
                    })}
                  </DotsContainer>
                )}
              >
                <Card />
                <Card />
              </Carousel>
            </Grid>
            <Grid item container direction="column" xs spacing={2}>
              <Grid item xs>
                <Text className="header1" text="프로젝트 후원" />
                <Text className="body2" text="관심있는 프로젝트에 후원하세요." />
              </Grid>
              <Grid item xs>
                <MoreBtn />
              </Grid>
            </Grid>
          </Grid>
        </Box>
      </Container>
=======
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
>>>>>>> dev
    </>
  );
}

export default Project;
