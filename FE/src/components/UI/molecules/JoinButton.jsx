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

const StyledLink = styled(Link)`
  display: inline-block;
  color: blue;
  text-decoration: none;
  &:focus,
  &:hover,
  &:visited,
  &:link,
  &:active {
    text-decoration: none;
  }
`;

function JoinButton() {
  return (
    <StyledBox>
      <StyledText className="linktext" text="아직도 세나기를 이용하고 있지 않으신가요? &nbsp;" />
      <StyledLink to="/signup" className="linktext">
        {'가입하기'}
      </StyledLink>
    </StyledBox>
  );
}

export default JoinButton;
