import { Outlet } from 'react-router-dom';
import Nav from './NavBar';
import { useState } from 'react';

const Layout = () => {
  //spinner
  const [loading, setLoading] = useState(false);
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
