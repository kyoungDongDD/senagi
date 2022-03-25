
import React from 'react';
import Banner from "../UI/organisms/Banner";
import NavBar from '../UI/organisms/NavBar';
import ReceiptImage from '../UI/organisms/ReceiptImage';

function UsageHistory() {
  return (
    <div>
      <NavBar />
      <Banner />
      <h2>기부금 사용 내역</h2>
      <ReceiptImage />
    </div>
  );
}

export default UsageHistory;
