import { useState, useEffect } from 'react';
import Grid from '@mui/material/Grid';
import styled from '@emotion/styled';
import Pagination from '../UI/organisms/Pagination';
import DonationInfoCard from '../UI/organisms/DonationInfoCard';
import BannerSlide from '../UI/organisms/BannerSlide';
import SelectBox from '../UI/molecules/SelectBox';
import { Outlet, useLocation } from 'react-router-dom';
import { getCampaignAll } from '../../api/campaignAPI';
import axios from 'axios';

function SearchResult() {
  // 페이지네이션
  const [posts, setPosts] = useState([]);
  const [limit, setLimit] = useState(12);
  const [page, setPage] = useState(1);
  const offset = (page - 1) * limit;

  //받아온 검색어 데이터
  const location = useLocation();
  const { keyword } = location.state;

  useEffect(() => {
    getCampaignAll().then((response) => {
      const campaignAll = response.content;
      console.log(campaignAll);
      setPosts(campaignAll);
    });
  }, []);

  return (
    <div>
      <BannerSlide />
      <Layout>
        <Outlet />
        <div>
          <H1>검색결과</H1>
        </div>

        <SelectOption>
          <SelectBox />
          {/* <label>
            페이지 당 표시할 게시물 수:&nbsp;
            <select
              type="number"
              value={limit}
              onChange={({ target: { value } }) => setLimit(Number(value))}
            >
              <option value="12">12</option>
              <option value="20">20</option>
              <option value="50">50</option>
              <option value="100">100</option>
            </select>
          </label> */}
        </SelectOption>
        <Grid container justifyContent={'space-evenly'}>
          {posts
            .slice(offset, offset + limit)
            .map(
              ({
                id,
                title,
                shelterName,
                targetDonation,
                thumbnailImageUrl,
                endDate,
                lastModifiedDate,
              }) => (
                <Grid item sm={7} md={5} lg={4} key={id}>
                  <div>
                    <DonationInfoCard
                      id={id}
                      title={title}
                      shelterName={shelterName}
                      thumbnailImageUrl={thumbnailImageUrl}
                      targetDonation={targetDonation}
                      endDate={endDate}
                      lastModifiedDate={lastModifiedDate}
                    ></DonationInfoCard>
                  </div>
                </Grid>
              ),
            )}
        </Grid>
        <Pagination total={posts.length} limit={limit} page={page} setPage={setPage} />
      </Layout>
    </div>
  );
}

export default SearchResult;

const Layout = styled.div`
  display: flex;
  flex-direction: column;
  max-width: 1200px;
  margin: 0 auto;
`;

const H1 = styled.h1`
  text-align: center;
  margin-top: 50px;
`;

const SelectOption = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  margin: 0px 15px 15px 15px;
  @media screen and (max-width: 1200px) {
    margin-left: 70px;
  }
`;
