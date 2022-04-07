import { useRef, useCallback } from 'react';
import Slick from 'react-slick';
import styled from '@emotion/styled';
import { css } from '@emotion/react';
import KeyboardArrowLeftIcon from '@mui/icons-material/KeyboardArrowLeft';
import KeyboardArrowRightIcon from '@mui/icons-material/KeyboardArrowRight';
import ZoomImage from './ZoomImage';

const ReceiptImage = (props) => {
  //영수증 이미지 props 받은 후 가공
  const { receipImages } = props;
  const receipImage = new Set(receipImages);
  const receipList = Array.from(receipImage);
  let receipArr = [];
  for (let i = 0; i < receipList.length; i++) {
    receipArr.push({
      src: `https://senagi.site/api/imgs/${receipList[i]}`,
    });
  }

  const slickRef = useRef(null);

  const settings = {
    dots: true,

    dotsClass: 'slick-dots slick-thumb',

    arrows: false,
    infinite: true,
    slidesToShow: 1,
    slidesToScroll: 1,

    customPaging: function (i) {
      const imgSrc = receipArr[i].src;
      return (
        <PagingAnchor>
          <Paging src={imgSrc} />
        </PagingAnchor>
      );
    },
  };

  const previous = useCallback(() => slickRef.current.slickPrev(), []);
  const next = useCallback(() => slickRef.current.slickNext(), []);
  return (
    <Wrap>
      <Slick ref={slickRef} {...settings}>
        {receipArr.map((v, index) => {
          return (
            <SlickItems key={index}>
              <img src={v.src} alt="이미지없음" />
            </SlickItems>
          );
        })}
      </Slick>
      <>
        <PrevButton onClick={previous}>
          <PrevIcon />
          <span className="hidden"></span>
        </PrevButton>

        <NextButton onClick={next}>
          <NextIcon />
          <span className="hidden"></span>
        </NextButton>
      </>
    </Wrap>
  );
};

export default ReceiptImage;

const Wrap = styled.div`
  position: relative;
  padding-bottom: 70px;
  width: 800px;
  overflow: hidden;

  .slick-slide {
    display: inline-block;
  }

  //추가한 커스텀 클래스
  // pagination 부분
  .slick-dots.slick-thumb {
    position: relative;
    bottom: 0;
    left: 46%;
    padding: 0;
    margin: 0;
    list-style: none;
    transform: translate(-50%);

    li {
      position: relative;
      display: inline-block;
      // 하단 이미지 간격조정
      margin-left: 25.5px;

      &.slick-active {
        span {
          filter: none;
        }
      }
    }
  }
`;

const SlickItems = styled.div`
  width: 100%;
  height: 450px;
  text-align: center;
  img {
    display: block;
    margin: 0px auto;
    max-width: 100%;
    height: 100%;
    vertical-align: top;
    object-fit: contain;
    border-radius: 20px;
  }
`;

const defaultButtonStyle = css`
  position: absolute;
  top: calc(50% - 50px);
  padding: 0;
  width: 30px;
  height: 30px;
  line-height: 1;
  border: none;
  border-radius: 50%;
  background: none;
  outline: none;
  cursor: pointer;
`;

const PrevButton = styled.button`
  ${defaultButtonStyle}
  left: 0;
`;

const NextButton = styled.button`
  ${defaultButtonStyle}
  right: 0;
`;

const defaultIconStyle = css`
  font-size: 30px;
  color: #dedede;

  &:focus,
  &:hover {
    color: #666;
  }
`;

const PrevIcon = styled(KeyboardArrowLeftIcon)`
  ${defaultIconStyle}
`;

const NextIcon = styled(KeyboardArrowRightIcon)`
  ${defaultIconStyle}
`;

const PagingAnchor = styled.a`
  display: block;
  width: 50px;
  height: 50px;

  img {
    width: 100%;
    height: 100%;
  }
`;

const Paging = styled.span`
  display: inline-block;
  width: 100%;
  height: 100%;
  vertical-align: middle;
  background: url(${(props) => props.src});
  background-size: 100% 100%;
  filter: grayscale(1);
`;
