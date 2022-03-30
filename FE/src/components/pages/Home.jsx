import Shelter from '../UI/organisms/Shelter';
import Project from '../UI/organisms/Project';
import BannerSlide from '../UI/organisms/BannerSlide';
import styled from '@emotion/styled';

function Home() {
  const Container = styled.div`
    background: linear-gradient(#fffcf3 60%, #fff 20%);
  `;
  return (
    <>
    <BannerSlide />
    <Container>
      <Shelter />
      <Project />
    </Container>
    </>
  );
}

export default Home;
