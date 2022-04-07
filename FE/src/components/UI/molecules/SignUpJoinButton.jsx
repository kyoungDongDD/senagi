import Text from '../atoms/Text';
import { Box } from '@mui/material';
import styled from '@emotion/styled';

const StyledBox = styled(Box)`
  display: inline-box;
  float: right;
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

function SignUpJoinButton() {
  return (
    <StyledBox>
      <StyledText className="linktext" text="보호소 회원으로 &nbsp;" />
      <StyledA
        className="linktext"
        onClick={() => window.open('https://forms.gle/yEsAD4UyDmoP1z7z8', '_blank')}
      >
        가입하기
      </StyledA>
    </StyledBox>
  );
}

export default SignUpJoinButton;
