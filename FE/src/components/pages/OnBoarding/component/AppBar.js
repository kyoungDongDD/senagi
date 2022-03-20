import MuiAppBar from "@mui/material/AppBar";

function AppBar(props) {
  return (
    <MuiAppBar
      style={{ background: "#fff" }}
      elevation={0}
      position="fixed"
      {...props}
    />
  );
}

export default AppBar;
