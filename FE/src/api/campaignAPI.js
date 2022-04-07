import axios from 'axios';

export async function getCampaignAll() {
  try {
    const response = await axios.get('https://j6b105.p.ssafy.io/api/campaigns');
    return response.data;
  } catch (err) {
    console.log('Falie to fetch user:', err);
    return 'Unknown';
  }
}

export async function getCampaignWord(searchWord) {
  try {
    const response = await axios.get(
      `https://j6b105.p.ssafy.io/api/campaigns?searchWord=${searchWord}`,
    );
    return response.data;
  } catch (err) {
    console.log('Falie to fetch user:', err);
    return 'Unknown';
  }
}

export async function getCampaignById(campaignId) {
  try {
    const response = await axios.get(`https://j6b105.p.ssafy.io/api/campaign/detail/${campaignId}`);
    return response.data;
  } catch (err) {
    console.log('Falie to fetch user:', err);
    return 'Unknown';
  }
}

// const api = axios.create({
//   baseURL: 'https://j6b105.p.ssafy.io/api',
// });

// class CampaignAPI {
//   //캠페인 리스트 전체 조회
//   getCampaignAll() {
//     return api.get(`/campaigns`);
//   }
//   //캠페인 리스트 전체 진행여부
//   getCampaignIsend() {
//     return api.get(`/campaigns?`);
//   }

//   // 캠페인 상세조회
//   getCampaignById(campaignId) {
//     return api.get(`/campaign/detail/:${campaignId}`);
//   }

//   // 생성한 캠페인리스트
//   getOwnedCampaign(page) {
//     return api.get(`/campaigns/owned/:${page}`);
//   }

//   // 캠페인 태그 조회
//   getCampaignTag(campaignId) {
//     return api.get(`/campaigns/tags/:${campaignId}`);
//   }
// }

// export default CampaignAPI;
