import { useState, useEffect } from 'react';
import { Grid, Tab, Box, MenuItem, FormControl, Select } from '@mui/material';
import { TabContext, TabList, TabPanel } from '@mui/lab';
import styled from '@emotion/styled';
import campaignAPI from '../../api/campaignAPI';
import BannerSlide from '../UI/organisms/BannerSlide';
import Pagination from '../UI/organisms/Pagination';
import DonationInfoCard from '../UI/organisms/DonationInfoCard';

function CampaignManagement() {
  const ListBox = styled.div`
    display: flex;
    flex-direction: column;
    max-width: 1200px;
    margin: 0 auto;
  `;

  const [value, setValue] = useState('false');
  const [range, setRange] = useState('registDate');
  const [posts, setPosts] = useState([]);
  const [page, setPage] = useState(1);
  const limit = 12;
  const offset = (page - 1) * limit;

  // 처음엔 진행중인 리스트 보여주기
  useEffect(() => {
    campaignAPI.getCampaignIsend('false', 'registDate', 'true').then((response) => {
      const campaign = response.data.content;
      setPosts(campaign);
    });
  }, []);

  // 진행, 종료 상태에 따라 리스트 보여주기
  const handleChange = (event, newValue) => {
    setValue(newValue);
    campaignAPI.getCampaignIsend(newValue, 'registDate', 'true').then((response) => {
      const campaign = response.data.content;
      setPosts(campaign);
    });
  };

  // 최신순, 조회순, 마감 임박순 정렬
  const rangeChange = (event, des) => {
    des = 'true';
    setRange(event.target.value);
    if (event.target.value === 'endDate') des = 'false';
    campaignAPI.getCampaignIsend(value, event.target.value, des).then((response) => {
      const campaign = response.data.content;
      setPosts(campaign);
    });
  };

  return (
    <div>
      <BannerSlide />
      <ListBox>
        <TabContext value={value}>
          <TabList
            sx={{ mt: 10, ml: 5, mb: 3 }}
            textColor="inherit"
            indicatorColor="secondary"
            onChange={handleChange}
          >
            <Tab label="진행" value="false" />
            <Tab label="종료" value="true" />
          </TabList>
          <Box sx={{ maxWidth: 140, ml: 5 }}>
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
          <TabPanel value="false">
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
                    </Grid>
                  ),
                )}
            </Grid>
          </TabPanel>
          <TabPanel value="true">
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
                    </Grid>
                  ),
                )}
            </Grid>
          </TabPanel>
        </TabContext>
        <Pagination total={posts.length} limit={limit} page={page} setPage={setPage} />
      </ListBox>
    </div>
  );
}

export default CampaignManagement;
