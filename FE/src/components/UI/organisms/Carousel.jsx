import React from "react";
import styled from "@emotion/styled";
import Slider from "react-slick";
import "slick-carousel/slick/slick.css"; 
import "slick-carousel/slick/slick-theme.css";


function Slide() {
    return (
      <div>
        <Container>
          <StyledSlider {...settings}>
            <div padding-top={'30px'}>
              <img
                src={ require('../../../assets/test4.jpg')}
                alt='logo'
                width= {'1000px'}
                height= {'278px'}
                object-fit= {'cover'}
              />
            </div>
            <div padding={'30px'}>
              <img
                src={ require('../../../assets/test1.jpg')}
                alt='logo'
                width= {'1000px'}
                height= {'278px'}
                object-fit= {'cover'}
              />
            </div>
            <div>
              <img
                  src={ require('../../../assets/test2.jpg')}
                  alt='logo'
                  width= {'1000px'}
                  height= {'278px'}
                  object-fit= {'cover'}
                />
            </div>
            <div>
              <img
                  src={ require('../../../assets/test3.jpg')}
                  alt='logo'
                  width= {'1000px'}
                  height= {'278px'}
                  object-fit= {'cover'}
                />
            </div>
          </StyledSlider>
        </Container>
      </div>
    );
}

export default Slide;

const settings = {
  dots: false,
  fade: true,
  infinite: true,
  speed: 1000,
  slidesToShow: 1,
  slidesToScroll: 1,
  autoplay: true,
  autoplaySpeed: 5000,
  centerMode: true,
  centerPadding: '0px',
};
//슬라이더 컨테이너
const Container = styled.div`
  background-color: #F4BA3499;
  margin-left: 1px;
  height: 250PX;
  padding-top: 30px;
  margin-bottom: 30px;
`
// 슬라이드 CSS
const StyledSlider = styled(Slider)`
    .slick-list {
      width: 1000px;
      margin: 0 auto;
      height: 342px;
    }
    @media (max-width: 1000px) {
      .slick-list {
        width: 450px;
        margin: 0 auto;
        height: 342px;
    }
    img {
      width: 450px;
    }
  }
  `
