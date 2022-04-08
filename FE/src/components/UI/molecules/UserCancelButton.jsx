import { Button } from '@mui/material';
import styled from '@emotion/styled';

const StyledButton = styled(Button)`
  background-color: black;

  &:focus,
  &:hover,
  &:visited,
  &:link,
  &:active {
    background-color: black;
  }

  .MuiTouchRipple-child {
    background-color: black;
  }
`;

function UserCancelButton(props) {
  return (
    <div>
      <StyledButton
        className="button"
        display="inline-block"
        type={props.type}
        fullWidth={props.fullWidth}
        variant={props.variant}
        sx={{ mt: 3, mb: 2, mr: 2 }}
        size={props.size}
        style={{
          backgroundColor: '#000000',
        }}
        onClick={props.func}
      >
        {props.text}
      </StyledButton>
    </div>
  );
}

export default UserCancelButton;
