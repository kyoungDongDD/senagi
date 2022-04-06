import { useState, useEffect } from 'react';
import MyPageList from '../UI/organisms/MyPageList';
import paymentAPI from '../../api/paymentAPI';

function MyPage() {
  const [myDonation, setMyDonation] = useState('');

  useEffect(() => {
    paymentAPI.mydonation().then((response) => {
      const myDonation = response.data;
      const Donations = Array.from(myDonation);
      setMyDonation(Donations);
    });
  }, []);

  return (
    <div>
      <MyPageList myDonation={myDonation} />
    </div>
  );
}

export default MyPage;
