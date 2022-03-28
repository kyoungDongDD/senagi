import { Outlet } from 'react-router-dom';
import Nav from './NavBar';

const Layout = () => {
  return (
    <div>
      <header>
        <Nav />
      </header>
      <main>
        <Outlet />
      </main>
    </div>
  );
};

export default Layout;
