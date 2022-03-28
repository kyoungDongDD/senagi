import { Link } from 'react-router-dom';
import GoogleLoginButton from '../atoms/GoogleLoginButton';
import Text from '../atoms/Text';
import JoinButton from '../molecules/JoinButton';
import UserButton from '../molecules/UserButton';
import { Box, TextField } from '@mui/material';
import styled from '@emotion/styled';

const StyledLink = styled(Link)`
  text-decoration: none;
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
        <StyledLink to="/home">
          <UserButton type="submit" fullWidth variant="contained" text="로그인" size="large" />
        </StyledLink>
        <GoogleLoginButton />
        <br />
        <JoinButton />
      </Box>
    </Box>
  );
}

export default UserLoginForm;
