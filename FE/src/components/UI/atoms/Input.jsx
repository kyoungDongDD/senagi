import { TextField } from '@mui/material';

function Input(props) {
  return (
    <div>
      <TextField
        id="outlined-basic"
        type={props.type}
        name={props.name}
        required={props.required}
        autoFocus={props.autoFocus}
        autoComplete={props.autoComplete}
        variant="outlined"
        color="primary"
        placeholder={props.placeholder}
      />
    </div>
  );
}

export default Input;
