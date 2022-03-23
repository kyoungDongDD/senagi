import Button from '../component/Button';
import Typography from '../component/Typography';
import IntroLayout from './IntroLayout';

const backgroundImage =
  'https://cdn.pixabay.com/photo/2017/12/27/14/02/friends-3042751_960_720.jpg';

export default function Intro() {
  return (
    <IntroLayout
      sxBackground={{
        backgroundImage: `url(${backgroundImage})`,
        backgroundColor: '#7fc7d9',
        backgroundPosition: 'center',
      }}
    >
      <img style={{ display: 'none' }} src={backgroundImage} alt="increase priority" />
      <Typography color="inherit" align="center" variant="h3" marked="center">
        블록체인 기반
        <br />
        유기동물 기부 플랫폼
        <br />
        '세상에 나쁜 기부는 없다'
      </Typography>
      <Typography color="inherit" align="center" variant="h5" sx={{ mb: 4, mt: { sx: 4, sm: 10 } }}>
        블록체인 기반 유기동물 기부 플랫폼
      </Typography>
      <Button
        color="warning"
        variant="contained"
        size="large"
        component="a"
        href="#"
        sx={{ minWidth: 200 }}
      >
        서비스 바로가기
      </Button>
      <Typography variant="body2" color="inherit" sx={{ mt: 2 }}>
        Discover the experience
      </Typography>
    </IntroLayout>
  );
}
