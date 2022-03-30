import GoogleLogin from '../../../assets/google_signin_buttons/btn_google_signin_light_normal_web@2x.png';

export const GoogleLoginButton = () => {
  return (
    <img
      src={GoogleLogin}
      width="222"
      alt="구글 로그인 버튼"
      onClick={() => {
        console.log('구글 로그인 버튼 연결 테스트');
      }}
    />
  );
};

export default GoogleLoginButton;
