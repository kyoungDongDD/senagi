import HashLoader from 'react-spinners/HashLoader';
import styled from '@emotion/styled';

function SignUpSpinner(props) {
  return (
    <div className={props.loading ? 'parentDisable' : ''} width="100%">
      <BackDiv>
        <HashLoader color={'#f4ba34'} size={150} />
      </BackDiv>
      <P>
        지갑을 생성 중입니다 <br /> 잠시만 기다려주세요.
      </P>
    </div>
  );
}

export default SignUpSpinner;

const BackDiv = styled.div`
  position: absolute;
  top: 47%;
  left: 50%;
  transform: translate(-50%, -50%);
  color: white;
  background: #666666;
  z-index: 1000;
`;

const P = styled.div`
  font-size: 32px;
  position: absolute;
  top: 59%;
  left: 40%;
  z-index: 1000;
  color: white;
`;
