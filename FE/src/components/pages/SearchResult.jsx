import { useState, useEffect } from 'react';
<<<<<<< HEAD
import Grid from '@mui/material/Grid';
import styled from '@emotion/styled';
import Pagination from '../UI/organisms/Pagination';
import DonationInfoCard from '../UI/organisms/DonationInfoCard';
import NavBar from '../UI/organisms/NavBar';
import BannerSlide from '../UI/organisms/BannerSlide';
import SelectBox from '../UI/molecules/SelectBox';
import { Outlet } from 'react-router-dom';

function SearchResult() {
=======
import { Grid, Box, MenuItem, FormControl, Select } from '@mui/material';
import styled from '@emotion/styled';
import Pagination from '../UI/organisms/Pagination';
import DonationInfoCard from '../UI/organisms/DonationInfoCard';
import BannerSlide from '../UI/organisms/BannerSlide';
import { useLocation } from 'react-router-dom';
import campaignAPI from '../../api/campaignAPI';

function SearchResult() {
  // 페이지네이션
>>>>>>> dev
  const [posts, setPosts] = useState([]);
  const [limit, setLimit] = useState(12);
  const [page, setPage] = useState(1);
  const offset = (page - 1) * limit;

<<<<<<< HEAD
  useEffect(() => {
    fetch('https://jsonplaceholder.typicode.com/posts')
      .then((res) => res.json())
      .then((data) => setPosts(data));
  }, []);

  return (
    <div>
      <NavBar />
      <BannerSlide />
      <Layout>
        <Outlet />
        <div>
          <h1>검색결과</h1>
        </div>

        <SelectOption>
          <SelectBox />
          <label>
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
          </label>
        </SelectOption>
        <Grid container justifyContent={'space-evenly'}>
          {posts.slice(offset, offset + limit).map(({ id, title, body }) => (
            <Grid item sm={7} md={5} lg={4}>
              <div key={id}>
                <DonationInfoCard>
                  {id}. {title}
                </DonationInfoCard>
              </div>
            </Grid>
          ))}
=======
  //받아온 검색어 데이터
  const location = useLocation();
  const { keyword } = location.state;

  const [range, setRange] = useState('registDate');

  useEffect(() => {
    campaignAPI.searchByKeyword(keyword, range, 'true').then((response) => {
      const campaign = response.data.content;
      console.log(campaign);
      setPosts(campaign);
    });
  }, [keyword, range]);

  // 최신순, 조회순, 마감 임박순 정렬
  const rangeChange = (event, des) => {
    des = 'true';
    setRange(event.target.value);
    if (event.target.value === 'endDate') des = 'false';
    campaignAPI.searchByKeyword(keyword, event.target.value, des).then((response) => {
      const campaign = response.data.content;
      setPosts(campaign);
    });
  };

  console.log(posts);

  return (
    <div>
      <BannerSlide />
      <Layout>
        <div>
          <H1>"{keyword}"에 대한 검색결과</H1>
        </div>
        <Box sx={{ maxWidth: 140, ml: 2 }}>
          <FormControl fullWidth>
            <Select sx={{ height: 35 }} value={range} onChange={rangeChange}>
              <MenuItem value={'registDate'} selected>
                최신순
              </MenuItem>
              <MenuItem value={'viewCount'}>조회순</MenuItem>
              <MenuItem value={'endDate'}>마감 임박순</MenuItem>
            </Select>
          </FormControl>
        </Box>
        <Grid container justifyContent={'flex-start'}>
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
                balance,
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
                      balance={balance}
                    ></DonationInfoCard>
                  </div>
                </Grid>
              ),
            )}
>>>>>>> dev
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
<<<<<<< HEAD
  align-items: center;
=======
>>>>>>> dev
  max-width: 1200px;
  margin: 0 auto;
`;

<<<<<<< HEAD
const SelectOption = styled.div`
  display: flex;
  justify-content: space-between;
  flex-direction: row;
=======
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
>>>>>>> dev
`;
