import { Outlet, Link } from 'react-router-dom';

function NavBar() {
  return (
    <div>
      <nav>
        <ul>
          <li>
            <Link to="/">캠페인</Link>
          </li>
          <li>
            <Link to="/welcome">사업소개</Link>
          </li>
          <li>
            <Link to="/mypage">마이페이지</Link>
          </li>
          <li>
            <Link to="/login">로그인</Link>
          </li>
        </ul>
      </nav>
      <hr />
      <Outlet />
    </div>
  );
}

export default NavBar;
