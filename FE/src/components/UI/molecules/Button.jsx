import { Button as Btn} from "@mui/material";

function Button(props) {
  return (
    <div>
      <Btn
        type={props.type}
        fullWidth={props.fullWidth}
        variant={props.variant}
        sx={{ mt: 3, mb: 2 }}
        size="large"
        style={{
          backgroundColor: "#F4BA34",
        }}
      >
        {props.text}
      </Btn>
    </div>
  );
}

export default Button;