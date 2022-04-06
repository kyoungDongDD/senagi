import { useState } from 'react';
import { css } from '@emotion/react';
import ClimbingBoxLoader from 'react-spinners/ClimbingBoxLoader';
import styled from '@emotion/styled';

// Can be a string as well. Need to ensure each key-value pair ends with ;
const override = css`
  display: block;
  position: absolute;
  margin: 0 auto;
  z-index: 100;
`;

function Spinner() {
  let [loading, setLoading] = useState(true);
  let [color, setColor] = useState('#ffffff');

  return (
    <div className="sweet-loading">
      <BackDiv>
        <ClimbingBoxLoader color={'#f4ba34'} css={override} size={30} />
      </BackDiv>
    </div>
  );
}

export default Spinner;

const BackDiv = styled.div`
  html {
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    left: 0;
    background: rgba(0, 0, 0, 0.5);
  }
`;
