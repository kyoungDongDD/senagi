import { useEffect, useState } from 'react';
import styled from '@emotion/styled';

const ProgressBar = (props) => {
  const { width, percent } = props;
  const [value, setValue] = useState(0);

  // let progress = percent * width;

  useEffect(() => {
    setValue(percent * width);
  }, [percent, width]);

  return (
    <ProgressDiv style={{ width: width }}>
      <Progress style={{ width: `${value}px` }} />
    </ProgressDiv>
  );
};

ProgressBar.defaultProps = {
  percent: 0.0,
};

export default ProgressBar;

const ProgressDiv = styled.div`
  display: flex;
  background-color: rgb(233, 233, 233);
  border-radius: 0.5rem;
  margin-top: 10px; /* bar 상하 간격 */
`;

const Progress = styled.div`
  background-color: #f4ba3499;
  height: 10px;
  border-radius: 1rem;
  transition: 1s ease;
  transition-delay: 0.5s;
`;
