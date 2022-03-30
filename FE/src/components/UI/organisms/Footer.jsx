import styled from '@emotion/styled';
import FacebookIcon from '@mui/icons-material/Facebook';
import InstagramIcon from '@mui/icons-material/Instagram';
import GitHubIcon from '@mui/icons-material/GitHub';

const Footer = () => {
  return (
    <div>
      <IconDiv>
        <div>
          <FacebookIcon
            fontSize="large"
            onClick={() => window.open('https://www.facebook.com/')}
            cursor="pointer"
          />
          <InstagramIcon
            fontSize="large"
            onClick={() => window.open('https://www.instagram.com/')}
            cursor="pointer"
          />
          <GitHubIcon
            fontSize="large"
            onClick={() => window.open('https://github.com/')}
            cursor="pointer"
          />
        </div>
        <p>COPYRIGHTⓒ 2022 SENAGI ALL RIGHTS RESERVED.</p>
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
  background-color: #f5fffa;
  width: 100%;
  height: 200px;
  margin-top: 100px;
`;
