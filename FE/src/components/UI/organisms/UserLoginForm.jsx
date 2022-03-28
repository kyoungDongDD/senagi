import GoogleLoginButton from '../atoms/GoogleLoginButton';
import UserButton from '../molecules/UserButton';
import Text from '../atoms/Text';
import { Link } from 'react-router-dom';

import { Typography, Box, TextField, FormControlLabel, Checkbox, Button } from '@mui/material';

import styled from '@emotion/styled';

const StyledText = styled(Text)`
  display: inline-block;

  color: black;
`;

const StyledLink = styled(Link)`
  display: inline-block;

  color: blue;

  text-decoration: none;

  &:focus,
  &:hover,
  &:visited,
  &:link,
  &:active {
    text-decoration: none;
  }
`;

function UserLoginForm() {
  const handleSubmit = (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    console.log({
      email: data.get('email'),
      password: data.get('password'),
    });
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
      <Box component="form" noValidate onSubmit={handleSubmit} sx={{ mt: 1 }}>
        <TextField
          margin="normal"
          required
          fullWidth
          id="email"
          label="Email Address"
          name="email"
          autoComplete="email"
          autoFocus
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
        />
        <Link to="/nav">
          <UserButton type="submit" fullWidth variant="contained" text="로그인" size="large" />
        </Link>
        <GoogleLoginButton />
        <br />
        <StyledText className="linktext" text="아직도 세나기를 이용하고 있지 않으신가요? &nbsp;" />
        <StyledLink to="/signup" className="linktext">
          {'가입하기'}
        </StyledLink>
      </Box>
    </Box>
  );
}

export default UserLoginForm;
