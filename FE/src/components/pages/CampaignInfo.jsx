import { useEffect, useState } from 'react';
import styled from '@emotion/styled';
import CampaignDetail from '../UI/organisms/CampaignDetail';
import UsageHistory from '../UI/organisms/UsageHistory';
import { useLocation } from 'react-router-dom';
import campaignAPI from '../../api/campaignAPI';

function CampaignInfo() {
  //소개, 사용내역 토글
  const [viewCalendar, setViewCalendar] = useState(true);
  const [currentClick, setCurrentClick] = useState(null);
  const [prevClick, setPrevClick] = useState(null);

  const GetClikc = (e) => {
    setCurrentClick(e.target.id);
  };

  // 상세조회 data
  const [compaignData, setCompaignData] = useState([]);

  const location = useLocation();
  const { pageId } = location.state;

  // DonationInfoCard에서 가져온 id 값을 매개변수로 getCampaignById 호출
  useEffect(() => {
    campaignAPI.getCampaignById(pageId).then((response) => {
      console.log(response);
      const compaignData = response.data;
      setCompaignData(compaignData);
      console.log(compaignData);
    });
  }, []);

  // 사용내역, 소개 버튼 밑줄 토글
  useEffect(
    (e) => {
      if (currentClick !== null) {
        let current = document.getElementById(currentClick);
        current.style.color = 'black';
        current.style.borderBottom = '2px solid';
        current.style.borderBottomColor = '#F4BA3499';
      }

      if (prevClick !== null) {
        let prev = document.getElementById(prevClick);
        prev.style.color = '#bebcbc';
        prev.style.borderBottom = 'none';
      }
      setPrevClick(currentClick);
    },
    [currentClick],
  );

  //day 계산
  const date1 = new Date(compaignData.endDate);
  const date2 = new Date(compaignData.lastModifiedDate);

  const diffDate = date1.getTime() - date2.getTime();

  const dateDays = Math.abs(diffDate / (1000 * 3600 * 24));

  //배너 이미지
  const shltername = compaignData.shelterName;

  function selectBanner(shltername) {
    let imageName = '';
    switch (shltername) {
      case '한국유기동물복지협회':
        imageName = 'test5.jpg';
        break;
      case '디팡보호소':
        imageName = 'test6.jpg';
        break;
      case '대전시온쉼터':
        imageName = 'test3.jpg';
        break;
      case '비글구조네트워크':
        imageName = 'test1.jpg';
        break;
      default:
        imageName = 'test4.jpg';
        break;
    }
    return imageName;
  }
  const banner = selectBanner(shltername);
  console.log(banner);

  return (
    <div>
      <BannerImg src={require(`../../assets/${banner}`)} />
      <BtnContainer>
        <div></div>
        <CategoryBox
          id="case1"
          onClick={(e) => {
            setViewCalendar(!false);
            GetClikc(e);
          }}
        >
          &nbsp;소개&nbsp;
        </CategoryBox>
        <CategoryBox2
          id="case2"
          onClick={(e) => {
            setViewCalendar(!true);
            GetClikc(e);
          }}
        >
          &nbsp;사용 내역&nbsp;
        </CategoryBox2>
        <div></div>
      </BtnContainer>
      {viewCalendar ? (
        <CampaignDetail
          targetDonation={compaignData.targetDonation}
          lastModifiedDate={compaignData.lastModifiedDate}
          endDate={compaignData.endDate}
          shelterName={compaignData.shelterName}
          title={compaignData.title}
          contentImageUrl={compaignData.contentImageUrl}
          thumbnailImageUrl={compaignData.thumbnailImageUrl}
          hashtags={compaignData.hashtags}
          balance={compaignData.balance}
          dday={dateDays}
        />
      ) : (
        <UsageHistory
          targetDonation={compaignData.targetDonation}
          lastModifiedDate={compaignData.lastModifiedDate}
          endDate={compaignData.endDate}
          shelterName={compaignData.shelterName}
          balance={compaignData.balance}
          pageId={pageId}
          dday={dateDays}
        />
      )}
    </div>
  );
}

export default CampaignInfo;

const BannerImg = styled.img`
  display: flex;
  width: 100%;
  height: 350px;
`;

const BtnContainer = styled.div`
  width: 99.85%;
  height: 50px;
  display: flex;
  justify-content: space-evenly;
  text-align: center;
  align-items: center;
  border: 1px solid #d7d7d7;
`;

const CategoryBox = styled.div`
  cursor: pointer;
  color: black;
  border-bottom: 2px solid;
  border-bottom-color: #f4ba3499;
`;

const CategoryBox2 = styled.div`
  cursor: pointer;
  color: #bebcbc;
  border-bottom: none;
`;
