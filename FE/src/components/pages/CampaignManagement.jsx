import Grid from "@mui/material/Grid"
import ImgCard from "../UI/organisms/DonationInfoCard";

function CampaignManagement() {
  return (
    <div>
      <Grid 
        container
        justifyContent={'space-evenly'}
      >
        <Grid item xs={3}>
          <ImgCard />
        </Grid>
        <Grid item xs={3}>
          <ImgCard />
        </Grid>
        <Grid item xs={3}>
          <ImgCard />
        </Grid>
      </Grid>
    </div>
  );
}

export default CampaignManagement;