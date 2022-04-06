import { useState } from 'react';
import { css } from '@emotion/react';
import ClimbingBoxLoader from 'react-spinners/ClimbingBoxLoader';

// Can be a string as well. Need to ensure each key-value pair ends with ;
const override = css`
  display: block;
  margin: 0 auto;
  border-color: red;
`;

function Spinner() {
  let [loading, setLoading] = useState(true);
  let [color, setColor] = useState('#ffffff');

  return (
    <div className="sweet-loading">
      <ClimbingBoxLoader color={'#f4ba34'} css={override} size={30} />
    </div>
  );
}

export default Spinner;
