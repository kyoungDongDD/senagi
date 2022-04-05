import { api } from './index';

class CampaignAPI {
  //캠페인 리스트 전체 조회
  async getCampaignAll() {
    const response = await api.get(`campaigns`);
    return response.data;
  }

  // 캠페인 타입별 리스트 조회
  async getCampaignByType(type) {
    const response = await api.get(`campaigns?type=${type}`);
    return response.data;
  }

  //캠페인 리스트 전체 진행여부
  async getCampaignIsend() {
    return api.get(`campaigns?`);
  }

  // 캠페인 상세조회
  async getCampaignById(campaignId) {
    const response = await api.get(`campaign/detail/${campaignId}`);
    return response.data;
  }

  // 생성한 캠페인리스트
  async getOwnedCampaign(page) {
    return api.get(`campaigns/owned/${page}`);
  }

  // 캠페인 태그 조회
  async getCampaignTag(campaignId) {
    return api.get(`campaigns/tags/${campaignId}`);
  }
}

export default new CampaignAPI();
