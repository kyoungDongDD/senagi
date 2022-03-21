import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Text from "../atoms/Text"
import UserButton from '../molecules/UserButton';
// import axios from 'axios';
import {
  Button,
  TextField,
  FormControlLabel,
  Checkbox,
  FormHelperText,
  Grid,
  Box,
} from '@mui/material/';
import styled from '@emotion/styled';


const FormHelperTexts = styled(FormHelperText)`
  width: 100%;
  padding-left: 16px;
  font-weight: 700;
  color: #d32f2f;
`;

function SignUpForm() {
  const [checked, setChecked] = useState(false);
  const [emailError, setEmailError] = useState('');
  const [passwordState, setPasswordState] = useState('');
  const [passwordError, setPasswordError] = useState('');
  const [nameError, setNameError] = useState('');
  const [registerError, setRegisterError] = useState('');
  const history = useNavigate();

  const handleAgree = (event) => {
    setChecked(event.target.checked);
  };

  // // API 요청
  // const onhandlePost = async (data) => {
  //   const { email, name, password } = data;
  //   const postData = { email, name, password };

  //   // post
  //   await axios
  //     .post('/user/signUp', postData)
  //     .then(function (response) {
  //       console.log(response, '성공');
  //       history.push('/login');
  //     })
  //     .catch(function (err) {
  //       console.log(err);
  //       setRegisterError('회원가입에 실패하였습니다. 다시한번 확인해 주세요.');
  //     });
  // };

  const handleSubmit = (e) => {
    e.preventDefault();

    const data = new FormData(e.currentTarget);
    const joinData = {
      email: data.get('email'),
      name: data.get('name'),
      password: data.get('password'),
      rePassword: data.get('rePassword'),
    };
    const { email, name, password, rePassword } = joinData;

    // 이메일 유효성 검사
    const emailRegex = /([\w-.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
    if (!emailRegex.test(email)) setEmailError('올바른 이메일 형식이 아닙니다.');
    else setEmailError('');

    // 비밀번호 유효성 검사
    const passwordRegex = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;
    if (!passwordRegex.test(password))
      setPasswordState('숫자+영문자+특수문자 조합으로 8자리 이상 입력해주세요!');
    else setPasswordState('');

    // 비밀번호 확인 검사
    if (password !== rePassword) setPasswordError('비밀번호가 일치하지 않습니다.');
    else setPasswordError('');

    // 이름 유효성 검사
    const nameRegex = /^[가-힣a-zA-Z]+$/;
    if (!nameRegex.test(name) || name.length < 1) setNameError('올바른 닉네임을 입력해주세요.');
    else setNameError('');

    // // 회원가입 동의 체크
    // if (!checked) alert('회원가입 약관에 동의해주세요.');

    // if (
    //   emailRegex.test(email) &&
    //   passwordRegex.test(password) &&
    //   password === rePassword &&
    //   nameRegex.test(name) &&
    //   checked
    // ) {
    //   onhandlePost(joinData);
    // }
  };
  return (
    <Box
        sx={{
          my: 8,
          mx: 4,
          display: 'flex',
          flexDirection: 'column',
          alignItems: 'center',
        }}
      >
      <Text
        className="header1"
        text="회원가입"
      />
      <Box component="form" noValidate onSubmit={handleSubmit} sx={{ mt: 1 }}>
        <Grid container spacing={1}>
          <Grid item xs={8}>
            <TextField
              required
              autoFocus
              fullWidth
              type="email"
              id="email"
              name="email"
              label="이메일 주소"
              error={emailError !== '' || false}
            />
          </Grid>
          <Grid item xs={4}>
            <Button
              type="submit"
              variant="outlined"
              style={{
                color: "#F4BA34",
                borderColor: "#F4BA34",
                backgroundColor: "white",
                height: "100%",
              }}
              fullWidth
              onClick={() => console.log("아이디중복체크API")}
            >중복확인</Button>
          </Grid>
        </Grid>
        <FormHelperTexts>{emailError}</FormHelperTexts>
        <TextField
          required
          fullWidth
          type="password"
          id="password"
          name="password"
          label="비밀번호 (숫자+영문자+특수문자 8자리 이상)"
          error={passwordState !== '' || false}
        />
        <FormHelperTexts>{passwordState}</FormHelperTexts>
        <TextField
          required
          fullWidth
          type="password"
          id="rePassword"
          name="rePassword"
          label="비밀번호 확인"
          error={passwordError !== '' || false}
        />
        <FormHelperTexts>{passwordError}</FormHelperTexts>
        <Grid container spacing={1}>
          <Grid item xs={8}>
            <TextField
              required
              fullWidth
              id="name"
              name="name"
              label="닉네임"
              error={nameError !== '' || false}
            />
          </Grid>
          <Grid item xs={4}>
            <Button
              type="submit"
              variant="outlined"
              style={{
                color: "#F4BA34",
                borderColor: "#F4BA34",
                backgroundColor: "white",
                height: "100%",
              }}
              fullWidth
              onClick={() => console.log("닉네임중복체크API")}
            >중복확인</Button>
          </Grid>
        </Grid>
        <FormHelperTexts>{nameError}</FormHelperTexts>
        <FormControlLabel
          control={<Checkbox onChange={handleAgree} color="primary" />}
          label="세나기의 회원가입 약관에 동의합니다."
        />
        <UserButton
          type="submit"
          fullWidth
          variant="contained"
          text="회원가입"
          size="large"
        />
        <FormHelperTexts>{registerError}</FormHelperTexts>
        <Button
          type="submit"
          fullWidth
          variant="text"
          disableRipple
          href="#"
          // 보호소 회원으로 가입하기 구글 폼
          style={{ backgroundColor: 'transparent' , justifyContent: "flex-end"}} >
          <span style={{color: 'black'}}>보호소 회원으로&nbsp;</span>가입하기
        </Button>
      </Box>
    </Box>
  );
}

export default SignUpForm;