import { useState } from 'react';
import { CssBaseline, Grid, Box, Paper, Tabs, Tab, Typography } from '@mui/material/';
import img2 from '../../assets/login_side.png';
import ShelterLoginForm from '../UI/organisms/ShelterLoginForm';
import UserLoginForm from '../UI/organisms/UserLoginForm';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import { makeStyles } from '@mui/styles';
import PropTypes from 'prop-types';
import styled from '@emotion/styled';

const tabStyles = makeStyles({
  customStyleOnTab: {
    fontSize: '15px',
    color: 'grey',
  },
  activeTab: {
    fontSize: '16px',
    fontWeight: '600',
    color: '#F4BA34',
  },
});

const StyledTab = styled(Tab)`
  .MuiTouchRipple-child {
    background-color: #f4ba34;
  }
`;

function TabPanel(props) {
  const { children, value, index, ...other } = props;

  return (
    <div
      role="tabpanel"
      hidden={value !== index}
      id={`${index}`}
      aria-labelledby={`${index}`}
      {...other}
    >
      {value === index && (
        <Box sx={{ p: 3 }}>
          <Typography>{children}</Typography>
        </Box>
      )}
    </div>
  );
}

TabPanel.propTypes = {
  children: PropTypes.node,
  index: PropTypes.number.isRequired,
  value: PropTypes.number.isRequired,
};

function Login() {
  const [value, setValue] = useState(0);
  const handleChange = (event, newValue) => {
    setValue(newValue);
  };
  const theme = createTheme();
  const tabClasses = tabStyles();
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
            backgroundImage: `url(${img2})`,
            backgroundRepeat: 'no-repeat',
            backgroundSize: 'cover',
            backgroundPosition: 'center',
          }}
        />
        <Grid item xs={12} sm={8} md={6} component={Paper} elevation={6} square>
          <Tabs
            TabIndicatorProps={{ style: { background: '#F4BA34' } }}
            sx={{
              mt: 8,
              mx: 4,
            }}
            variant="fullWidth"
            value={value}
            onChange={handleChange}
            aria-label="basic tabs example"
            centered
          >
            <StyledTab
              label={
                <span className={value === 0 ? tabClasses.activeTab : tabClasses.customStyleOnTab}>
                  일반 회원
                </span>
              }
              sx={{
                '& .MuiTabs-flexContainer': {
                  backgroundColor: '#f4ba34',
                },
              }}
              id="0"
              aria-controls="0"
            />
            <StyledTab
              label={
                <span className={value === 1 ? tabClasses.activeTab : tabClasses.customStyleOnTab}>
                  보호소 회원
                </span>
              }
              sx={{
                '& .MuiTabs-flexContainer': {
                  backgroundColor: '#f4ba34',
                },
              }}
              id="1"
              aria-controls="1"
            />
          </Tabs>
          <TabPanel value={value} index={0}>
            <UserLoginForm />
          </TabPanel>
          <TabPanel value={value} index={1}>
            <ShelterLoginForm />
          </TabPanel>
        </Grid>
      </Grid>
    </ThemeProvider>
  );
}

export default Login;
