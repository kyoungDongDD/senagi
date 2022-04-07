import styled from '@emotion/styled';
import Slider from 'react-slick';
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';

const settings = {
  className: 'slider',
  dots: false,
  arrows: true,
  fade: true,
  infinite: true,
  speed: 800,
  slidesToShow: 1,
  slidesToScroll: 1,
  autoplay: true,
  autoplaySpeed: 5000,
};

function BannerSlide() {
  return (
    <div>
      <Container>
        <StyledSlider {...settings}>
          <div>
            <img
              src={require('../../../assets/test1.jpg')}
              alt="logo"
              width={'100%'}
              height={'330px'}
              object-fit={'fit'}
            />
          </div>
          <div>
            <img
              src={require('../../../assets/test2.jpg')}
              alt="logo"
              width={'100%'}
              height={'330px'}
              object-fit={'fit'}
            />
          </div>
          <div>
            <img
              src={require('../../../assets/test3.jpg')}
              alt="logo"
              width={'100%'}
              height={'330px'}
              object-fit={'fit'}
            />
          </div>
        </StyledSlider>
      </Container>
    </div>
  );
}

export default BannerSlide;

//슬라이더 컨테이너
const Container = styled.div`
  background-color: #f4ba3499;
  margin-left: 1px;
  height: 300px;
  padding-top: 30px;
  margin-bottom: 20px;
  padding: 25px 180px 0px 180px;
  @media screen and (max-width: 900px) {
    padding: 25px 0px 0px 0px;
  }
`;
// 슬라이드 CSS
const StyledSlider = styled(Slider)`
  .slick-list {
    height: 330px;
  }
`;
