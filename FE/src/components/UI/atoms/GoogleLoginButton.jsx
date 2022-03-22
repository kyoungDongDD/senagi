import GoogleLogin from '../../../assets/google_signin_buttons/btn_google_signin_light_normal_web@2x.png';
import { Button } from "@mui/material";

function GoogleLoginButton(props) {
  return (
    <Button
    type={props.type}
    fullWidth={props.fullWidth}
    variant={props.variant}
    sx={{ mt: 3, mb: 2 }}
    size={props.size}
    style={{
      backgroundColor: "#F4BA34",
    }}
  >
    Google 계정으로 로그인
  </Button>
  );
};

export default GoogleLoginButton;
