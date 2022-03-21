import { useState, useEffect } from "react";
import Grid from "@mui/material/Grid"
import styled from "@emotion/styled";
import Pagination from "../UI/organisms/Pagination";
import DonationInfoCard from "../UI/organisms/DonationInfoCard";
import NavBar from "../UI/organisms/NavBar";
import Slide from "../UI/organisms/Carousel";
import { Outlet } from 'react-router-dom';


function SearchResult() {
  const [posts, setPosts] = useState([]);
  const [limit, setLimit] = useState(10);
  const [page, setPage] = useState(1);
  const offset = (page - 1) * limit;

  useEffect(() => {
    fetch("https://jsonplaceholder.typicode.com/posts")
      .then((res) => res.json())
      .then((data) => setPosts(data));
  }, []);

  return (
    <>
      <NavBar />
      <Slide />
      <Layout>
        <Outlet />
        <div>
          <h1>검색결과</h1>
        </div>
        
        <label>
          페이지 당 표시할 게시물 수:&nbsp;
          <select
            type="number"
            value={limit}
            onChange={({ target: { value } }) => setLimit(Number(value))}
          >
            <option value="10">10</option>
            <option value="12">12</option>
            <option value="20">20</option>
            <option value="50">50</option>
            <option value="100">100</option>
          </select>
        </label>

        <Grid 
          container
          justifyContent={'space-evenly'}
        >
        {posts.slice(offset, offset + limit).map(({ id, title, body }) => (
            <Grid item xs={12} md={4}> 
              <div key={id}>
                  <DonationInfoCard>
                    {id}. {title}
                  </DonationInfoCard>
              </div>
            </Grid>
          ))}
        </Grid>
        <Pagination
          total={posts.length}
          limit={limit}
          page={page}
          setPage={setPage}
        />
      </Layout>
    </>
  );
}

const Layout = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  max-width: 1200px;
  margin: 0 auto;
`;

export default SearchResult;