import Slide from '../UI/organisms/ArticleImg';
import ConditionSection from '../UI/organisms/ConditionSection';
import Grid from '@mui/material/Grid';
import ImgCard from '../UI/organisms/DonationInfoCard';
import { Box } from '@mui/material';

function CampaignManagement() {
  return (
    <div>
      <Slide />
      <ConditionSection />
      <Box sx={{ pl: 40, pr: 40, pt: 10 }}>
        <Grid container spacing={4} justifyContent={'flex-start'}>
          <Grid item xs={4}>
            <ImgCard />
          </Grid>
          <Grid item xs={4}>
            <ImgCard />
          </Grid>
          <Grid item xs={4}>
            <ImgCard />
          </Grid>
          <Grid item xs={4}>
            <ImgCard />
          </Grid>
          <Grid item xs={4}>
            <ImgCard />
          </Grid>
          <Grid item xs={4}>
            <ImgCard />
          </Grid>
          <Grid item xs={4}>
            <ImgCard />
          </Grid>
          <Grid item xs={4}>
            <ImgCard />
          </Grid>
        </Grid>
      </Box>
    </div>
  );
}

export default CampaignManagement;
