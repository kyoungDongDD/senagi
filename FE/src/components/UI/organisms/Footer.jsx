import styled from '@emotion/styled';
import FacebookIcon from '@mui/icons-material/Facebook';
import InstagramIcon from '@mui/icons-material/Instagram';
import GitHubIcon from '@mui/icons-material/GitHub';
import { grey } from '@mui/material/colors';
const Footer = () => {
  return (
    <div>
      <IconDiv>
        <div>
          <FacebookIcon
            sx={{ color: grey[600] }}
            fontSize="large"
            onClick={() => window.open('https://www.facebook.com/')}
            cursor="pointer"
          />
          <InstagramIcon
            sx={{ color: grey[600] }}
            fontSize="large"
            onClick={() => window.open('https://www.instagram.com/')}
            cursor="pointer"
          />
          <GitHubIcon
            sx={{ color: grey[600] }}
            fontSize="large"
            onClick={() => window.open('https://github.com/')}
            cursor="pointer"
          />
        </div>
        <P>김경동 김민현 이동호</P>
        <P>박해인 백철연 이아현</P>
        <P>COPYRIGHTⓒ 2022 SENAGI ALL RIGHTS RESERVED.</P>
      </IconDiv>
    </div>
  );
};

export default Footer;

const IconDiv = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background-color: #141414;
  width: 100%;
  height: 200px;
  margin-top: 100px;
`;

const P = styled.p`
  font-size: 13px;
  color: #767676;
`;
