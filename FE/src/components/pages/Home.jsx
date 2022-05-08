import Shelter from '../UI/organisms/Shelter';
import Project from '../UI/organisms/Project';
import BannerSlide from '../UI/organisms/BannerSlide';
<<<<<<< HEAD
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
=======
import Article from '../UI/organisms/MainArticle';
import styled from '@emotion/styled';
import Footer from '../UI/organisms/Footer';

function Home() {
  const Container = styled.div`
    max-width: 1100px;
    margin: 0 auto;
  `;

  return (
    <>
      <BannerSlide />
      <Container>
        <Article />
        <Shelter />
        <Project />
      </Container>
      <Footer />
>>>>>>> dev
    </>
  );
}

export default Home;
