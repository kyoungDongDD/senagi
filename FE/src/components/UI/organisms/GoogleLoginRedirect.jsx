import { useEffect } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { login, socialAuthSuccess } from '../../../store/user';
import jwt from 'jwt-decode';

function GoogleLoginRedirect() {
  const navigate = useNavigate();
  const dispatch = useDispatch();

  useEffect(() => {
    let temp = new URL(window.location.href).searchParams.get('jwtToken');
    // console.log(temp);

    const token = `{"jwtToken":"${temp}"}`;
    // const json = `{"jwtToken":"${temp}"}`;
    // const token = JSON.parse(json);
    // console.log('type of token', typeof token);
    // console.log('token', token);

    if (token) {
      dispatch(login(token));
      const userInfo = jwt(token);
      // console.log('decoded userInfo', typeof userInfo);
      dispatch(socialAuthSuccess(userInfo));
      alert('로그인에 성공했습니다.');
      navigate('/home');
    } else {
      alert('jwt 토큰이 존재하지 않습니다.');
    }
  }, []);

  // 로딩 스피너 구현
  return <div>로그인중입니다!</div>;
}

export default GoogleLoginRedirect;
