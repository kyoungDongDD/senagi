import { useEffect } from 'react';
import CampaignAPI from '../../api/campaignAPI';
import { getCampaignAll } from '../../api/campaignAPI';
import axios from 'axios';

function Testpage() {
  useEffect(() => {
    getCampaignAll().then((response) => {
      const campaignAll = response.content;
      console.log(campaignAll);
    });
  }, []);

  return (
    <>
      <div>아다다다다</div>
    </>
  );
}

export default Testpage;
