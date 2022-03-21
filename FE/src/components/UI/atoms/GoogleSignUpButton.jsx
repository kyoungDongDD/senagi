import GoogleSignUp from "../../../assets/google_signin_buttons/btn_google_signin_light_normal_web@2x.png"
// Google SignUp 버튼 이미지 제작해야함

export const GoogleSignUpButton = () => {
  return (
    <img
      src={GoogleSignUp}
      width="222"
      alt="구글 회원가입 버튼"
      onClick={() => {console.log("구글 회원가입 버튼 연결 테스트")}}
    />
  );
}

export default GoogleSignUpButton;