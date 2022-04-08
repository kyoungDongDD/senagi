import { Outlet } from 'react-router-dom';
import Nav from './NavBar';
<<<<<<< HEAD

const Layout = () => {
=======
import { useState } from 'react';

const Layout = () => {
  //spinner
  const [loading, setLoading] = useState(false);
>>>>>>> dev
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
