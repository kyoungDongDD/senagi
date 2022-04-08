<<<<<<< HEAD
import { Button as Btn} from "@mui/material";
=======
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
>>>>>>> dev

function UserButton(props) {
  return (
    <div>
<<<<<<< HEAD
      <Btn
=======
      <StyledButton
        className="button"
>>>>>>> dev
        type={props.type}
        fullWidth={props.fullWidth}
        variant={props.variant}
        sx={{ mt: 3, mb: 2 }}
        size={props.size}
        style={{
<<<<<<< HEAD
          backgroundColor: "#F4BA34",
        }}
      >
        {props.text}
      </Btn>
=======
          backgroundColor: '#F4BA34',
        }}
        onClick={props.func}
      >
        {props.text}
      </StyledButton>
>>>>>>> dev
    </div>
  );
}

<<<<<<< HEAD
export default UserButton;
=======
export default UserButton;
>>>>>>> dev
