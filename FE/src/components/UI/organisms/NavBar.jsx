import { useState, useEffect } from 'react';
import { styled, alpha } from '@mui/material/styles';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import Menu from '@mui/material/Menu';
import MenuIcon from '@mui/icons-material/Menu';
import Container from '@mui/material/Container';
import Button from '@mui/material/Button';
import Tooltip from '@mui/material/Tooltip';
import MenuItem from '@mui/material/MenuItem';
import InputBase from '@mui/material/InputBase';
import SearchIcon from '@mui/icons-material/Search';

import { Link, useNavigate } from 'react-router-dom';

const pages = ['캠페인', '사업소개', '마이페이지'];

const NavBar = () => {
  const [anchorElNav, setAnchorElNav] = useState(null);
  const [keyword] = useState('');

  const navigate = useNavigate();

  const handleOpenNavMenu = (event) => {
    setAnchorElNav(event.currentTarget);
  };

  const handleCloseNavMenu = (event) => {
    setAnchorElNav(null);
    // 페이지 링크
    const page = event.target.innerText;
    switch (page) {
      case '캠페인':
        navigate('/searchresult', {
          state: {},
        });
        break;
      case '사업소개':
        navigate('/');
        break;
      case '마이페이지':
        navigate('/mypage');
        break;
      default:
        navigate('/home');
        break;
    }
  };

  // 검색 기능

  // 검색어 전달과 함께 페이지 이동
  const onCheckEnter = (e) => {
    const keyword = e.target.value;
    if (e.key === 'Enter') {
      navigate('/searchresult', {
        state: {
          keyword: keyword,
        },
      });
      window.scrollTo(0, 300);
    }
  };

  // navbar 디자인
  const Search = styled('div')(({ theme }) => ({
    position: 'relative',
    borderRadius: theme.shape.borderRadius,
    backgroundColor: alpha(theme.palette.common.white, 0.15),
    '&:hover': {
      backgroundColor: alpha(theme.palette.common.white, 0.25),
    },
    marginLeft: 0,
    width: '100%',
    [theme.breakpoints.up('sm')]: {
      marginLeft: theme.spacing(1),
      width: 'auto',
    },
  }));

  const SearchIconWrapper = styled('div')(({ theme }) => ({
    padding: theme.spacing(0, 2),
    height: '100%',
    position: 'absolute',
    pointerEvents: 'none',
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
  }));

  const StyledInputBase = styled(InputBase)(({ theme }) => ({
    color: '#000000',
    '& .MuiInputBase-input': {
      padding: theme.spacing(1, 1, 1, 0),
      // vertical padding + font size from searchIcon
      paddingLeft: `calc(1em + ${theme.spacing(4)})`,
      transition: theme.transitions.create('width'),
      width: '100%',
      [theme.breakpoints.up('sm')]: {
        width: '0ch',
        '&:hover': {
          width: '20ch',
        },
      },
    },
  }));

  return (
    // navbar배경색 변경 #F4BA3499
    <AppBar position="static" style={{ background: 'inherit' }}>
      <Container maxWidth="xl">
        <Toolbar disableGutters>
          <Typography
            variant="h6"
            noWrap
            component="div"
            sx={{ mr: 2, display: { xs: 'none', md: 'flex' } }}
          >
            <Link to="/home">
              <img src={require('../../../assets/logo.png')} alt="logo" />
            </Link>
          </Typography>

          <Box sx={{ flexGrow: 1, display: { xs: 'flex', md: 'none' } }}>
            <IconButton
              size="large"
              aria-label="account of current user"
              aria-controls="menu-appbar"
              aria-haspopup="true"
              onClick={handleOpenNavMenu}
            >
              <MenuIcon />
            </IconButton>
            <Menu
              id="menu-appbar"
              anchorEl={anchorElNav}
              anchorOrigin={{
                vertical: 'bottom',
                horizontal: 'left',
              }}
              keepMounted
              transformOrigin={{
                vertical: 'top',
                horizontal: 'left',
              }}
              open={Boolean(anchorElNav)}
              onClose={handleCloseNavMenu}
              sx={{
                display: { xs: 'block', md: 'none' },
              }}
            >
              {pages.map((page) => (
                <MenuItem key={page} onClick={handleCloseNavMenu}>
                  <Typography textAlign="center">{page}</Typography>
                </MenuItem>
              ))}
            </Menu>
          </Box>
          <Typography
            variant="h6"
            noWrap
            component="div"
            sx={{ flexGrow: 1, display: { xs: 'flex', md: 'none' } }}
          >
            <Link to="/">
              <img src={require('../../../assets/logo.png')} alt="logo" />
            </Link>
          </Typography>
          <Box
            sx={{
              flexGrow: 1,
              display: { xs: 'none', md: 'flex' },
              justifyContent: 'center',
              alignItems: 'center',
            }}
          >
            {pages.map((page) => (
              <Button
                key={page}
                onClick={handleCloseNavMenu}
                // 폰트 색,사이즈 변경
                sx={{
                  my: 2,
                  color: 'black',
                  display: 'block',
                  fontSize: '18px',
                  fontFamily: 'GM',
                  paddingX: '40px',
                }}
              >
                {page}
              </Button>
            ))}
            {/* 닉네임아이콘/닉네임/로그아웃 */}
            {/* <AccountCircleIcon 
              color='action'
              padding='1000px'
            />
            <p>나는김경동</p> */}
          </Box>
          <Box sx={{ flexGrow: 0 }}>
            <Tooltip title="Enter를 눌러 찾기">
              <Search>
                <SearchIconWrapper>
                  <SearchIcon color="action" />
                </SearchIconWrapper>
                <StyledInputBase
                  placeholder="검색하기"
                  inputProps={{ 'aria-label': 'search' }}
                  name="SearchKeyword"
                  onKeyPress={onCheckEnter}
                />
              </Search>
            </Tooltip>
          </Box>
        </Toolbar>
      </Container>
    </AppBar>
  );
};
export default NavBar;
