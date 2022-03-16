import * as React from "react";
import { Routes, Route, Link } from "react-router-dom";
import Home from "./components/pages/Home"
import NavBar from "./components/UI/organisms/NavBar";
import Welcome from "./components/pages/Welcome";
import MyPage from "./components/pages/MyPage";
import Login from "./components/pages/Login";

export default function App() {
  return (
    <div>
      <h1>세나기 리액트 시작하쥬아</h1>
      <Routes>
        <Route path="/" element={<NavBar />}>
          <Route index element={<Home />} />
          <Route path="welcome" element={<Welcome />} />
          <Route path="mypage" element={<MyPage />} />
          <Route path="login" element={<Login />} />

          {/* 404 Not Found*/}
          <Route path="*" element={<NoMatch />} />
        </Route>
      </Routes>
    </div>
  );
}


// 404 Not Found
function NoMatch() {
  return (
    <div>
      <h2>Nothing to see here!</h2>
      <p>
        <Link to="/">Go to the home page</Link>
      </p>
    </div>
  );
}
