import Button from "@mui/material/Button";
import Container from "@mui/material/Container";
import Typography from "../component/Typography";

function Inquiry() {
  return (
    <Container
      component="section"
      sx={{
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        my: 9,
      }}
    >
      <Button
        sx={{
          border: "4px solid currentColor",
          borderRadius: 0,
          height: "auto",
          py: 2,
          px: 5,
        }}
      >
        <Typography variant="h4" component="span">
          궁금한 점이나 도움이 필요하신가요?
        </Typography>
      </Button>
      <Typography variant="subtitle1" sx={{ my: 3 }}>
        위에 메일과 메시지를 남겨주시면 답변해드릴게요!
      </Typography>
    </Container>
  );
}

export default Inquiry;
