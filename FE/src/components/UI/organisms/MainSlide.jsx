import Slider from 'react-slick';
import Grid from '@mui/material/Grid';
import DonationInfoCard from './DonationInfoCard';
import ArrowForwardIcon from '@mui/icons-material/ArrowForward';
import '../../../styles/MainSlide.css';

import styled from '@emotion/styled';

const settings = {
  dots: true,
  fade: true,
  infinite: true,
  speed: 500,
  slidesToShow: 1,
  slidesToScroll: 1,
};

const settingsButtom = {
  arrows: true,
  infinite: true,
  speed: 500,
  slidesToShow: 3,
  slidesToScroll: 3,
};

function MainSlide() {
  return (
    <div>
      {/* slide top */}
      <Grid container direction="row" justifyContent="space-between" alignItems="center">
        <Grid item xs={6}>
          <Infobox>
            <p className="header2">보호소 후원</p>
            <p className="body1">관심있는 보호소에 후원하세요.</p>
            <span>더보기</span>
            <ArrowForwardIcon fontSize="small" />
          </Infobox>
        </Grid>
        <Grid item xs={6}>
          <Slider {...settings} dotsClass="test-css">
            <div>
              <DonationInfoCard />
            </div>
            <div>
              <h3>2</h3>
            </div>
            <div>
              <h3>3</h3>
            </div>
          </Slider>
        </Grid>
      </Grid>
      {/* slide buttom */}
      {/* <Grid>
          <Slider {...settingsButtom}>
            <div>
              <h3>1</h3>
            </div>
            <div>
              <h3>2</h3>
            </div>
            <div>
              <h3>3</h3>
            </div>
          </Slider>
        </Grid> */}
    </div>
  );
}

export default MainSlide;

const Infobox = styled.div`
  margin-left: 60px;
`;
