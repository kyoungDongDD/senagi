import { useEffect } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import Toast from '../atoms/SweetAlert';
import { login, socialAuthSuccess } from '../../../store/user';
import jwt from 'jwt-decode';

function GoogleLoginRedirect() {
  const navigate = useNavigate();
  const dispatch = useDispatch();

  useEffect(() => {
    // jwtToken URL에서 추출
    let temp = new URL(window.location.href).searchParams.get('jwtToken');
    // jwtToken string -> json 형식으로 맞춰주기 위해 변경
    const token = `{"jwtToken":"${temp}"}`;
    if (token) {
      dispatch(login(token));
      const userInfo = jwt(token);
      dispatch(socialAuthSuccess(userInfo));
      Toast.fire({
        icon: 'success',
        title: '로그인 되었습니다!',
      });
      navigate('/home');
    } else {
      Toast.fire({
        icon: 'error',
        title: '로그인 실패 :(',
      });
    }
  }, []);

  // 로딩 스피너 구현
  return <div>로그인중입니다!</div>;
}

export default GoogleLoginRedirect;
