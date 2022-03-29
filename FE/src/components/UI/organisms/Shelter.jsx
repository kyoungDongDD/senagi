import Carousel from 'nuka-carousel';
import styled from '@emotion/styled';
import { Grid, Box } from '@mui/material';
import Text from '../atoms/Text';
import MoreBtn from '../molecules/MoreButton';
import Card from './DonationInfoCard';

function Shelter() {
  const Container = styled.div`
    background: linear-gradient(#fff 80%, #fffcf3 20%);
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
            <Grid item container direction="column" xs spacing={2}>
              <Grid item xs>
                <Text className="header1" text="보호소 후원" />
                <Text className="body2" text="관심있는 보호소에 후원하세요." />
              </Grid>
              <Grid item xs>
                <MoreBtn />
              </Grid>
            </Grid>
            <Grid item xs>
              <div style={{ width: '40%', margin: 'auto' }}>
                <Carousel
                  autoplay={true}
                  wrapAround={true}
                  slideListMargin={0}
                  slidesToScroll={1}
                  slidesToShow={1}
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
                  <Card />
                </Carousel>
              </div>
            </Grid>
          </Grid>
        </Box>
      </Container>
    </>
  );
}

export default Shelter;
