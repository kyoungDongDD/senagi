import GoogleLogo from '../../../assets/g-logo.png';
import styled from '@emotion/styled';

const StyledButton = styled.a`
  border: none;
  text-decoration: none;
  border-radius: 2px;
  box-shadow: 0 1px 1px 0 rgba(0, 0, 0, 0.24), 0 0 1px 0 rgba(0, 0, 0, 0.12);
  display: flex;
  align-items: center;
  padding: 1px;
  color: rgba(0, 0, 0, 0.54);
  &:hover {
    box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.24), 0 0 2px 0 rgba(0, 0, 0, 0.12);
  }
  &:active {
    background: #eeeeee;
  }
  background: #fff;
  width: 100%;
  height: 42.25px;
`;

const StyledImg = styled.img`
  width: 2rem;
  margin-left: 1rem;
`;
const StyledP = styled.p`
  margin: auto;
  font-size: 0.9375rem;
  font-family: 'Roboto';
  font-weight: 500;
  letter-spacing: 0.2px;
  text-align: left;
  white-space: nowrap;
`;

function GoogleLoginButton() {
  return (
    <StyledButton href="https://senagi.site:8080/oauth2/authorization/google" target="_blank">
      <StyledImg src={GoogleLogo} alt="GoogleLogo" />
      <StyledP>Google 계정으로 로그인</StyledP>
    </StyledButton>
  );
}

export default GoogleLoginButton;
