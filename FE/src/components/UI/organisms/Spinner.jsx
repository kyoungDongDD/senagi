import { useState } from 'react';
import { css } from '@emotion/react';
import ClimbingBoxLoader from 'react-spinners/ClimbingBoxLoader';
import styled from '@emotion/styled';
import SpinnerCSS from './Spinner.css';

// const overlayBox = css`
//   position: absolute;
//   top: 50%;
//   left: 50%;
//   transform: translate(-50%, -50%);
//   color: white;
//   background: #666666;
//   opacity: 0.8;
//   z-index: 1000;
// `;

function Spinner(props) {
  return (
    <div className={props.loading ? 'parentDisable' : ''} width="100%">
      {/* // <div css={props.loading ? 'parentDisable' : ''} width="100%"> */}
      {/* // <div className={spinner} width="100%"> */}
      <BackDiv>
        <ClimbingBoxLoader color={'#f4ba34'} size={30} />
      </BackDiv>
    </div>
  );
}

export default Spinner;

const BackDiv = styled.div`
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  color: white;
  background: #666666;
  z-index: 1000;
`;
