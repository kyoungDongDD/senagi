import { Button } from '@mui/material';
import styled from '@emotion/styled';

const StyledButton = styled(Button)`
  background-color: #f4ba34;

  &:focus,
  &:hover,
  &:visited,
  &:link,
  &:active {
    background-color: #f4ba34;
  }

  .MuiTouchRipple-child {
    background-color: #f4ba34;
  }
`;

function UserButton(props) {
  return (
    <div>
      <StyledButton
        className="button"
        type={props.type}
        fullWidth={props.fullWidth}
        variant={props.variant}
        sx={{ mt: 3, mb: 2 }}
        size={props.size}
        style={{
          backgroundColor: '#F4BA34',
        }}
        onClick={props.func}
      >
        {props.text}
      </StyledButton>
    </div>
  );
}

export default UserButton;
