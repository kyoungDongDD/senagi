import NavBar from '../UI/organisms/NavBar';
import BannerSlide from '../UI/organisms/BannerSlide';
import MainSlide from '../UI/organisms/MainSlide';

function Home() {
  return (
    <div>
      <NavBar />
      <BannerSlide />
      <h2 className="body1">Home</h2>
      <MainSlide />

    </div>
  );
}

export default Home;