import Shelter from '../UI/organisms/Shelter';
import Project from '../UI/organisms/Project';
import BannerSlide from '../UI/organisms/BannerSlide';
import styled from '@emotion/styled';
import Footer from '../UI/organisms/Footer';

function Home() {
  const Container = styled.div`
    padding: 0 200px;
  `;

  return (
    <>
      <BannerSlide />
      <Container>
        <Shelter />
        <Project />
      </Container>
      <Footer />
    </>
  );
}

export default Home;
