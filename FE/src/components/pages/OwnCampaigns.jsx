import { useState, useEffect } from 'react';
import OwnCampaignList from '../UI/organisms/OwnCampaignList';
import campaignAPI from '../../api/campaignAPI';

function OwnCampaigns() {
  const [campaignList, setcampaignList] = useState('');

  useEffect(() => {
    campaignAPI.getOwnedCampaign().then((response) => {
      const campaignsData = response.data.content;
      const campaigns = Array.from(campaignsData);
      setcampaignList(campaigns);
    });
  }, []);

  return (
    <div>
      <OwnCampaignList campaignList={campaignList} />
    </div>
  );
}

export default OwnCampaigns;
