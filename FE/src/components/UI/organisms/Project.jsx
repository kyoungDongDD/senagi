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
    </>
  );
}

export default Project;
