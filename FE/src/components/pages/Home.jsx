import React from 'react';
import ProgressButton from '../UI/molecules/ProgressButton';
import CampaignCard from '../UI/organisms/CampaignCard';

function Home() {
  return (
    <div>
      <ProgressButton/>
      <h2 className="body3">Home</h2>
      <CampaignCard />
    </div>
  );
}

export default Home;