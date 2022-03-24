import Box from '@mui/material/Box';
import Link from '@mui/material/Link';
import AppBar from '../component/AppBar';
import Toolbar from '../component/Toolbar';

const rightLink = {
  fontSize: 16,
  color: 'black',
  ml: 3,
};

function OnHeader() {
  return (
    <div>
      <AppBar position="fixed">
        <Toolbar sx={{ justifyContent: 'space-between' }}>
          <Box sx={{ flex: 1 }} />
          <Link variant="h6" underline="none" color="black" sx={{ fontSize: 24 }}>
            {'SENAGI'}
          </Link>
          <Box sx={{ flex: 1, display: 'flex', justifyContent: 'flex-end' }}>
            <Link color="inherit" variant="h6" underline="none" href="/login" sx={rightLink}>
              {'Login'}
            </Link>
            <Link
              variant="h6"
              underline="none"
              href="/signup"
              sx={{ ...rightLink, color: '#F6C85D' }}
            >
              {'Sign Up'}
            </Link>
          </Box>
        </Toolbar>
      </AppBar>
    </div>
  );
}

export default OnHeader;
