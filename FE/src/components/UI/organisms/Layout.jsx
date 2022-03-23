import { Outlet } from 'react-router-dom';
import Nav from './NavBar';

const Layout = () => {
  return (
    <div>
      <header>
        <Nav />
      </header>
      <main style={{ padding: 10 }}>
        <Outlet />
      </main>
    </div>
  );
};

export default Layout;
