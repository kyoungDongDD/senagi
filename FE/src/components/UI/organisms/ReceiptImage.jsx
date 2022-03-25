import React from "react";
import styled from "@emotion/styled";
import Slider from "react-slick";
import "slick-carousel/slick/slick.css"; 
import "slick-carousel/slick/slick-theme.css";



function ReceiptImage() {
    return (
      <div>
        <Container>
          <StyledSlider {...settings}>
            <div>
              <img
                src={''}
                alt='사진없음'
                width= {''}
                height= {'278px'}
                object-fit= {'fit'}
              />
            </div>
            <div>
              <img
                src={''}
                alt='사진없음'
                width= {''}
                height= {'278px'}
                object-fit= {'fit'}
              />
            </div>
          </StyledSlider>
        </Container>
      </div>
    );
}

export default ReceiptImage;

const settings = {
  dots: true,
  infinite: true,
  speed: 500,
  slidesToShow: 1,
  slidesToScroll: 1
};
//슬라이더 컨테이너
const Container = styled.div`
  margin-left: 1px;
  height: 250PX;
  padding-top: 30px;
  margin-bottom: 30px;
  padding: 25px 0px 0px 0px;
`
// 슬라이드 CSS
const StyledSlider = styled(Slider)`
    /* .slick-list {
      height: 342px;
    } */
  `