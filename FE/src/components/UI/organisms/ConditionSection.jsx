import Box from '@mui/material/Box';
import ConditionBtn from '../atoms/ConditionButton';

function ConditionSection() {
  return (
    <>
      <Box
        sx={{
          display: 'flex',
          flexDirection: 'column',
          alignItems: 'left',
          pl: 40,
        }}
      >
        <ConditionBtn />
      </Box>
    </>
  );
}

export default ConditionSection;
