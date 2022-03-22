
import React, {useEffect, useState} from 'react'
import styled from '@emotion/styled';

const Banner = () => {

  const [currentClick, setCurrentClick] = useState(null);
  const [prevClick, setPrevClick] = useState(null);
  
  const GetClick = (e) => {
    setCurrentClick(e.target.id);
    console.log(e.target.id);
  };

  useEffect(
      (e) => {
        if (currentClick !== null) {
          let current = document.getElementById(currentClick);
          console.log(current);
          current.style.color = "black";
          current.style.borderBottom = "2px solid";
          current.style.borderBottomColor = "#F4BA3499";
        }
  
        if (prevClick !== null) {
          let prev = document.getElementById(prevClick);
          prev.style.color = "#bebcbc";
          prev.style.borderBottom = "none";
        }
        setPrevClick(currentClick);
      },
      [currentClick]
      );
  
    return (
      <>
      <BannerImg 
      src={ require('../../../assets/test1.jpg')} />
        <BtnContainer>
            <div></div>
            <CategoryBox id="case1" onClick={GetClick}>
            &nbsp;소개&nbsp;
            </CategoryBox>
            <CategoryBox id="case2" onClick={GetClick}>
            &nbsp;사용 내역&nbsp;
            </CategoryBox>
            <div></div>
        </BtnContainer>
      </>
    );
  };


export default Banner;

const BannerImg = styled.img`
  display: flex;
  width: 100%;
  height: 350px;
`

const BtnContainer = styled.div`
  width: 99.8%;
  height: 50px;
  display: flex;
  justify-content: space-evenly;
  text-align: center;
  align-items: center;
  border: 1px solid;
`

const CategoryBox = styled.div`
  cursor: pointer;
`