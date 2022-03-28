import React from 'react';
// import axios from 'axios';
import {
  CssBaseline,
  Grid,
  Paper
} from '@mui/material/';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import SignUpForm from '../UI/organisms/SignUpForm';
import img1 from "../../assets/signup_side.png";


function SignUp() {
  const theme = createTheme();

  return (
    <ThemeProvider theme={theme}>
      <Grid container component="main" sx={{ height: '100vh' }}>
        <CssBaseline />
        <Grid
            item
            xs={false}
            sm={4}
            md={6}
            sx={{
              backgroundImage: `url(${img1})`,
              backgroundRepeat: 'no-repeat',
              backgroundColor: (t) =>
                t.palette.mode === 'light' ? t.palette.grey[50] : t.palette.grey[900],
              backgroundSize: 'cover',
              backgroundPosition: 'center',
            }}
          />
        <Grid item xs={12} sm={8} md={6} component={Paper} elevation={6} square>
          <SignUpForm />
        </Grid>
      </Grid>
    </ThemeProvider>
  );
};

export default SignUp;