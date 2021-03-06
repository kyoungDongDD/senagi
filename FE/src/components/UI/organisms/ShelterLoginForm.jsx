<<<<<<< HEAD
import GoogleLoginButton from '../atoms/GoogleLoginButton';
import UserButton from '../molecules/UserButton';
import Text from '../atoms/Text';

import {
  Link,
  Typography,
  Box,
  TextField,
  FormControlLabel,
  Checkbox,
  Button,
} from '@mui/material';

function ShelterLoginForm() {
  const handleSubmit = (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    console.log({
      email: data.get('email'),
      password: data.get('password'),
    });
=======
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import Text from '../atoms/Text';
import Toast from '../atoms/SweetAlert';
import ShelterJoinButton from '../molecules/ShelterJoinButton';
import UserButton from '../molecules/UserButton';
import { Box, TextField } from '@mui/material';
import AccountsAPI from '../../../api/accountsAPI';
import { authSuccess, login } from '../../../store/user';
import jwt from 'jwt-decode';

function ShelterLoginForm() {
  const [joinData, setJoinData] = useState([]); // 로그인 정보 저장
  const navigate = useNavigate();
  const dispatch = useDispatch();

  // 회원 정보 저장
  const setJoin = (e, names) => {
    setJoinData(Object.assign(joinData, { [names]: e.target.value }));
  };

  const logIn = async (event) => {
    event.preventDefault();
    const { userId, userPw } = joinData;
    const postData = {
      principal: userId,
      credential: userPw,
    };

    await AccountsAPI.shelterLogin(postData)
      .then((response) => {
        // 로그인 토큰 저장
        dispatch(login(response.data));
        // 토큰 디코드
        const token = response.data.jwtToken;
        const userInfo = jwt(token);
        // 토큰의 유저 정보 store에 저장
        dispatch(authSuccess(userInfo));
        Toast.fire({
          icon: 'success',
          title: '로그인 되었습니다!',
        });
        navigate('/home');
      })
      .catch((error) => {
        console.log(error);
        Toast.fire({
          icon: 'error',
          title: '로그인 실패 :(',
        });
      });
>>>>>>> dev
  };

  return (
    <Box
      sx={{
        mx: 4,
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
      }}
    >
      <Text className="header1" text="로그인" />
<<<<<<< HEAD
      <Box component="form" noValidate onSubmit={handleSubmit} sx={{ mt: 1 }}>
=======
      <Box component="form" noValidate onSubmit={logIn} sx={{ mt: 1 }}>
>>>>>>> dev
        <TextField
          margin="normal"
          required
          fullWidth
          id="email"
          label="Email Address"
          name="email"
          autoComplete="email"
          autoFocus
<<<<<<< HEAD
=======
          onChange={(e) => setJoin(e, 'userId')}
>>>>>>> dev
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
<<<<<<< HEAD
        />
        <UserButton type="submit" fullWidth variant="contained" text="로그인" size="large" />
=======
          onChange={(e) => setJoin(e, 'userPw')}
        />
        <UserButton
          type="submit"
          fullWidth
          variant="contained"
          text="로그인"
          size="large"
          func={logIn}
        />
        <ShelterJoinButton />
>>>>>>> dev
      </Box>
    </Box>
  );
}

export default ShelterLoginForm;
