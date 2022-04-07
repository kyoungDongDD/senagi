import ClimbingBoxLoader from 'react-spinners/ClimbingBoxLoader';
import styled from '@emotion/styled';

function Spinner(props) {
  return (
    <div className={props.loading ? 'parentDisable' : ''} width="100%">
      {/* // <div css={props.loading ? 'parentDisable' : ''} width="100%"> */}
      {/* // <div className={spinner} width="100%"> */}
      <BackDiv>
        <ClimbingBoxLoader color={'#f4ba34'} size={30} />
      </BackDiv>
      <P>블록체인을 통한 기부가 진행중 입니다.</P>
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

const P = styled.div`
  font-size: 32px;
  position: absolute;
  top: 100%;
  left: 4%;
  z-index: 1000;
  color: white;
  width: 100%;
  text-align: center;
`;
