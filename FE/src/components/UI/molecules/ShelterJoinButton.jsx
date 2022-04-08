import { Link } from 'react-router-dom';
import Text from '../atoms/Text';
import { Box } from '@mui/material';
import styled from '@emotion/styled';

const StyledBox = styled(Box)`
  justify-content: center;
  display: flex;
  align-items: center;
`;

const StyledText = styled(Text)`
  display: inline-block;
  color: black;
  margin: auto 0 auto auto;
`;

const StyledA = styled.a`
  display: inline-block;
  color: blue;
  text-decoration: none;
  margin: auto 0 auto auto;
  cursor: pointer;
`;

function ShelterJoinButton() {
  return (
    <StyledBox>
      <StyledText className="linktext" text="아직도 세나기를 이용하고 있지 않으신가요? &nbsp;" />
      <StyledA
        className="linktext"
        onClick={() => window.open('https://forms.gle/yEsAD4UyDmoP1z7z8', '_blank')}
      >
        가입하기
      </StyledA>
    </StyledBox>
  );
}

export default ShelterJoinButton;
