import * as React from 'react';
import Button from '@mui/material/Button';
import Box from '@mui/material/Box';
import ButtonGroup from '@mui/material/ButtonGroup';

const buttons = [
  <Button key="one">진행</Button>,
  <Button key="two">종료</Button>,
];

export default function ProgressButton() {
  return (
    <Box
      sx={{
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        '& > *': {
          m: 1,
        },
      }}
    >
      <ButtonGroup color="inherit" size="large" aria-label="large button group">
        {buttons}
      </ButtonGroup>
    </Box>
  );
}


{/* <Btn
type={props.type}
fullWidth={props.fullWidth}
variant={props.variant}
sx={{ mt: 3, mb: 2 }}
size={props.size}
style={{
  backgroundColor: "#F4BA34",
}}
>
{props.text}
종료
</Btn> */}