import { Routes, Route, Link } from 'react-router-dom';
import { Global } from '@emotion/react';
import Layout from './components/UI/organisms/Layout';
import Home from './components/pages/Home';
import Welcome from './components/pages/OnBoarding/Welcome';
import MyPage from './components/pages/MyPage';
import Login from './components/pages/Login';
import SignUp from './components/pages/SignUp';
import CampaignManagement from './components/pages/CampaignManagement';
import Introduce from './components/pages/Introduce';
import SearchResult from './components/pages/SearchResult';
import CampaignInfo from './components/pages/CampaignInfo';
import OwnCampaigns from './components/pages/OwnCampaigns';
import commonStyles from './styles/commonStyles';
import GoogleLoginRedirect from './components/UI/organisms/GoogleLoginRedirect';

export default function App() {
  return (
    <div>
      <Global styles={commonStyles} />
      <Routes>
        <Route path="/" element={<Welcome />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<SignUp />} />
        <Route path="/sociallogin" element={<GoogleLoginRedirect />} />

        <Route element={<Layout />}>
          <Route path="/home" element={<Home />} />
          <Route path="/campaign" element={<CampaignManagement />} />
          <Route path="/introduce" element={<Introduce />} />
          <Route path="/mypage" element={<MyPage />} />
          <Route path="/owncampaigns" element={<OwnCampaigns />} />
          <Route path="/searchresult" element={<SearchResult />} />
          <Route path="/campaigninfo/:campaignId" element={<CampaignInfo />} />

          {/* 404 Not Found*/}
          <Route path="/*" element={<NoMatch />} />
        </Route>
      </Routes>
    </div>
  );
}

// 404 Not Found
function NoMatch() {
  return (
    <div>
      <h2>Nothing to see here!</h2>
      <p>
        <Link to="/">Go to the home page</Link>
      </p>
    </div>
  );
}
