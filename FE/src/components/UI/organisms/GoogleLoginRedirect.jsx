import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { login, authSuccess } from '../../../store/user';
import jwt from 'jwt-decode';

function GoogleLoginRedirect() {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  useEffect(() => {
    handleResponse();
  });

  const handleResponse = async () => {
    const token = this.$route.query.code;
    // const error = this.$route.query.error;
    console.log(token.toString());

    // 코드가 존재할 경우 사용자가 동의한 것
    if (token) {
      // 로그인 토큰 저장
      dispatch(login(token));
      // 토큰 디코드
      const userInfo = jwt(token);
      // 토큰의 유저 정보 store에 저장
      dispatch(authSuccess(userInfo));
      alert('로그인에 성공했습니다.');
      navigate('/home');
      // 회원가입 여부 확인 API 필요?
    } else {
      console.log('인증코드 못 받아옴');
    }
  };
  // 로딩 스피너 구현
  return <div></div>;
}

export default GoogleLoginRedirect;
