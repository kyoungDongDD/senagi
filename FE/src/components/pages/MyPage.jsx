import { useState, useEffect } from 'react';
import MyPageList from '../UI/organisms/MyPageList';
import paymentAPI from '../../api/paymentAPI';
// import useHistory from 'react-router-dom';

function MyPage() {
  const [myDonation, setMyDonation] = useState('');

  useEffect(() => {
    paymentAPI.mydonation().then((response) => {
      const myDonation = response.data;
      const Donations = Array.from(myDonation);
      setMyDonation(Donations);
    });
  }, []);

  // const history = useHistory();
  // history.push(0);

  return (
    <>
      <MyPageList myDonation={myDonation} />
    </>
  );
}

export default MyPage;
