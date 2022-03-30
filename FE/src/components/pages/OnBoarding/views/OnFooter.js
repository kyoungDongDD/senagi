import Box from "@mui/material/Box";
import Grid from "@mui/material/Grid";
import Link from "@mui/material/Link";
import Container from "@mui/material/Container";
import GitHubIcon from "@mui/icons-material/GitHub";
import FacebookIcon from "@mui/icons-material/Facebook";
import InstagramIcon from "@mui/icons-material/Instagram";
import Typography from "../component/Typography";

export default function OnFooter() {
  return (
    <Typography component="footer" sx={{ display: "flex", bgcolor: "#fff3e0" }}>
      <Container sx={{ my: 8, display: "flex" }}>
        <Grid container spacing={5}>
          <Grid item xs={6} sm={4} md={3}>
            <Grid
              container
              direction="column"
              justifyContent="flex-end"
              spacing={2}
              sx={{ height: 120 }}
            >
              <Grid item sx={{ display: "flex" }}>
                <GitHubIcon />
                <FacebookIcon />
                <InstagramIcon />
              </Grid>
              <Grid item>대전광역시 유성구 동서대로 98-39</Grid>
              <Grid item>ssafy@ssafy.com | 010-1234-5678</Grid>
            </Grid>
          </Grid>
          <Grid item xs={6} sm={4} md={2}>
            <Typography variant="h6" marked="left" gutterBottom>
              Legal
            </Typography>
            <Box component="ul" sx={{ m: 0, listStyle: "none", p: 0 }}>
              <Box component="li" sx={{ py: 0.5 }}>
                <Link href="#">고객센터</Link>
              </Box>
              <Box component="li" sx={{ py: 0.5 }}>
                <Link href="#">개인정보처리방침</Link>
              </Box>
            </Box>
          </Grid>
          <Grid item xs={6} sm={8} md={4}></Grid>
          <Grid item>
            <Typography variant="caption">
              Copyright © 2022 SENAGI. All Rights Reserved.
            </Typography>
          </Grid>
        </Grid>
      </Container>
    </Typography>
  );
}
