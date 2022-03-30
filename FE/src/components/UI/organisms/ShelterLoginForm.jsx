import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import Text from '../atoms/Text';
import JoinButton from '../molecules/JoinButton';
import UserButton from '../molecules/UserButton';
import { Box, TextField } from '@mui/material';
import AccountsAPI from '../../../api/accountsAPI';
import { login, logout } from '../../../store/user';

function ShelterLoginForm() {
  const [joinData, setJoinData] = useState([]); // 로그인 정보 저장
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const user = useSelector((state) => state.user.value)

  // 회원 정보 저장
  const setJoin = (e, names) => {
    setJoinData(Object.assign(joinData, { [names]: e.target.value }));
  };

  const logIn = async(event) => {
    event.preventDefault();
    // console.log('logIn API', joinData);
    const { userId, userPw } = joinData;
    const postData = {
      principal: userId,
      credential: userPw,
    };
    
    await AccountsAPI.shelterLogin(postData)
    .then((response) => {
      if (response.status === 200) {
        alert('로그인에 성공했습니다.');
        // console.log(response);
        dispatch(login(response.data))
        // 로그인 후 메인페이지로 navigate하는 function, 디버깅을 위해 주석 처리 해놓음
        // navigate('/');
      } else {
        alert('로그인에 실패했습니다.');
      }
    })
    .catch((error) => {
      console.log(error);
      alert('로그인에 실패했습니다.');
    });    
  };

  // 임시로 로그아웃 function 연결해놓음 nav에 연결하면 삭제
  const logOut = () => {
    dispatch(logout())
  }

  return (
    <Box
      sx={{
        mx: 4,
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
      }}
    >
      {/* 로그인 확인 디버깅용 코드 */}
      {/* <h1>로그인 확인 디버깅용</h1>
      <p> ID : {user.principal} </p>
      <p> PW token : {user.token} </p> */}
      <Text className="header1" text="로그인" />
      <Box component="form" noValidate onSubmit={logIn} sx={{ mt: 1 }}>
        <TextField
          margin="normal"
          required
          fullWidth
          id="email"
          label="Email Address"
          name="email"
          autoComplete="email"
          autoFocus
          onChange={(e) => setJoin(e, 'userId')}
        />
        <TextField
          margin="normal"
          required
          fullWidth
          name="password"
          label="Password"
          type="password"
          id="password"
          autoComplete="current-password"
          onChange={(e) => setJoin(e, 'userPw')}
        />
        <UserButton type="submit" fullWidth variant="contained" text="로그인" size="large" func={logIn}/>
        {/* 임시 */}
        <UserButton type="submit" fullWidth variant="contained" text="로그아웃" size="large" func={logOut}/>
        <JoinButton />
      </Box>
    </Box>
  );
}

export default ShelterLoginForm;
