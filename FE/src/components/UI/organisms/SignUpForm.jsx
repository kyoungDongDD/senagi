import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Text from '../atoms/Text';
import Toast from '../atoms/SweetAlert';
import UserButton from '../molecules/UserButton';
import SignUpJoinButton from '../molecules/SignUpJoinButton';
import AccountsAPI from '../../../api/accountsAPI';
import SignUpSpinner from './SignUpSpinner';
import { validateEmail, validateName, validatePwd } from '../../../utils/validation';
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
  const [joinData, setJoinData] = useState([]); // 회원가입 정보 저장
  const [idError, setIdError] = useState('');
  const [idFl, setIdFl] = useState(false);
  const [passwordState, setPasswordState] = useState('');
  const [passwordError, setPasswordError] = useState('');
  const [nameError, setNameError] = useState('');
  const [nameFl, setNameFl] = useState(false);
  const [checkedFl, setCheckedFl] = useState(false);
  const [registerError, setRegisterError] = useState('');
  const navigate = useNavigate();
  // spinner
  const [loading, setLoading] = useState(false);
  // 나중에 loader 추가하기
  // 회원가입
  const signUp = async () => {
    const { userId, userName, userPw } = joinData;
    const postData = {
      principal: userId,
      credential: userPw,
      name: userName,
    };
    setLoading(true);
    await AccountsAPI.signUp(postData)
      .then((response) => {
        if (response.status === 201) {
          setLoading(false);
          Toast.fire({
            icon: 'success',
            title: '가입에 성공했습니다!',
          });
          navigate('/login');
        } else {
          setLoading(false);
          Toast.fire({
            icon: 'error',
            title: '이미 사용중인 아이디입니다.',
          });
        }
      })
      .catch((error) => {
        console.log(error);
        setLoading(false);
        setRegisterError('회원가입에 실패하였습니다. 다시 확인해 주세요.');
        Toast.fire({
          icon: 'error',
          title: '회원가입에 실패하였습니다. 다시 확인해 주세요.',
        });
      });
  };

  // 회원 정보 저장
  const setJoin = (e, names) => {
    // 아이디, 닉네임 입력할때 -> 중복체크 false로 변경
    if (names === 'userId') setIdFl(false);
    if (names === 'userName') setNameFl(false);
    setJoinData(Object.assign(joinData, { [names]: e.target.value }));
  };

  const handleAgree = (event) => {
    setCheckedFl(event.target.checked);
  };

  // 아이디 중복체크
  const idCheck = async (event) => {
    event.preventDefault();
    // 이메일 regex 형식 검사
    if (!validateEmail(joinData.userId)) {
      setIdFl(false);
      setIdError('올바른 이메일 형식이 아닙니다.');
      return;
    } else {
      setIdFl(true);
      setIdError('');
    }

    // 이메일 중복 검사
    await AccountsAPI.checkEmail(joinData.userId)
      .then((response) => {
        if (response.status === 200) {
          setIdFl(true);
          Toast.fire({
            icon: 'success',
            title: '사용할 수 있는 아이디입니다.',
          });
        } else {
          setIdFl(false);
          Toast.fire({
            icon: 'error',
            title: '이미 사용중인 아이디입니다.',
          });
        }
      })
      .catch((error) => {
        console.log(error);
      });
  };

  // 닉네임 중복체크
  const nameCheck = async (event) => {
    event.preventDefault();
    // 닉네임 regex 형식 검사
    if (!validateName(joinData.userName)) {
      setNameError('올바른 닉네임 형식이 아닙니다.');
      return;
    } else setNameError('');

    // 닉네임 중복 검사
    await AccountsAPI.checkName(joinData.userName)
      .then((response) => {
        setNameFl(true);
        Toast.fire({
          icon: 'success',
          title: '사용할 수 있는 닉네임입니다.',
        });
      })
      .catch((error) => {
        console.log(error);
        Toast.fire({
          icon: 'error',
          title: '이미 사용중인 닉네임입니다.',
        });
      });
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    // 필수값 체크
    // 이메일 유효성 검사
    if (!validateEmail(joinData.userId)) setIdError('올바른 이메일 형식이 아닙니다.');
    else setIdError('');

    // 비밀번호 유효성 검사
    if (!validatePwd(joinData.userPw))
      setPasswordState('숫자+영문자+특수문자 조합으로 8자리 이상 입력해주세요.');
    else setPasswordState('');

    // 비밀번호 확인 검사
    if (joinData.userPw !== joinData.userPwOk) setPasswordError('비밀번호가 일치하지 않습니다.');
    else setPasswordError('');

    // 이름 유효성 검사
    if (!validateName(joinData.userName) || joinData.userName.length < 1)
      setNameError('올바른 닉네임을 입력해주세요. 한글, 영문자만 가능합니다');
    else setNameError('');

    // 회원가입 동의 체크
    if (!checkedFl)
      Toast.fire({
        icon: 'info',
        title: '회원가입 약관에 동의해주세요.',
      });

    // 모든 조건을 만족하면 회원가입 function 실행
    if (
      idFl &&
      validatePwd(joinData.userPw) &&
      joinData.userPw === joinData.userPwOk &&
      nameFl &&
      checkedFl
    ) {
      signUp();
    }
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
      <Text className="header1" text="회원가입" />
      <Box component="form" noValidate sx={{ mt: 1 }}>
        <Grid container spacing={1}>
          <Grid item xs={8}>
            <TextField
              required
              autoFocus
              fullWidth
              type="id"
              id="id"
              name="id"
              label="이메일 주소"
              error={idError !== '' || false}
              onChange={(e) => setJoin(e, 'userId')}
            />
          </Grid>
          <Grid item xs={4}>
            <Button
              type="submit"
              variant="outlined"
              style={{
                color: '#F4BA34',
                borderColor: '#F4BA34',
                backgroundColor: 'white',
                height: '100%',
              }}
              fullWidth
              onClick={idCheck}
            >
              중복확인
            </Button>
          </Grid>
        </Grid>
        <FormHelperTexts>{idError}</FormHelperTexts>
        <TextField
          sx={{ mt: 1 }}
          required
          fullWidth
          type="password"
          id="password"
          name="password"
          label="비밀번호 (숫자+영문자+특수문자 8자리 이상)"
          error={passwordState !== '' || false}
          onChange={(e) => setJoin(e, 'userPw')}
        />
        <FormHelperTexts>{passwordState}</FormHelperTexts>
        <TextField
          sx={{ mt: 1 }}
          required
          fullWidth
          type="password"
          id="rePassword"
          name="rePassword"
          label="비밀번호 확인"
          error={passwordError !== '' || false}
          onChange={(e) => setJoin(e, 'userPwOk')}
        />
        <FormHelperTexts>{passwordError}</FormHelperTexts>
        <Grid container spacing={1}>
          <Grid item xs={8}>
            <TextField
              sx={{ mt: 1 }}
              required
              fullWidth
              id="name"
              name="name"
              label="닉네임 (한글, 영문 대소문자)"
              error={nameError !== '' || false}
              onChange={(e) => setJoin(e, 'userName')}
            />
          </Grid>
          <Grid item xs={4}>
            <Button
              sx={{ mt: 1 }}
              type="submit"
              variant="outlined"
              style={{
                color: '#F4BA34',
                borderColor: '#F4BA34',
                backgroundColor: 'white',
                height: '100%',
              }}
              fullWidth
              onClick={nameCheck}
            >
              중복확인
            </Button>
          </Grid>
        </Grid>
        <FormHelperTexts>{nameError}</FormHelperTexts>
        <FormControlLabel
          control={<Checkbox onChange={handleAgree} color="primary" />}
          label="세나기의 회원가입 약관에 동의합니다."
        />
        <UserButton
          sx={{ mt: 1 }}
          type="submit"
          fullWidth
          variant="contained"
          text="회원가입"
          size="large"
          func={handleSubmit}
        />
        <FormHelperTexts>{registerError}</FormHelperTexts>
        <SignUpJoinButton />
        {/* <Button
          type="submit"
          fullWidth
          variant="text"
          disableRipple
          onClick={() => window.open('https://forms.gle/yEsAD4UyDmoP1z7z8', '_blank')}
          style={{ backgroundColor: 'transparent', justifyContent: 'flex-end' }}
        >
          <span className="linktext">보호소 회원으로&nbsp;</span>가입하기
        </Button> */}
      </Box>
      {loading && <SignUpSpinner loading={loading} />}
    </Box>
  );
}

export default SignUpForm;
