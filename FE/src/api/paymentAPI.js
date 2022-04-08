import { api, tokenApi, withdrawApi } from './index';

class PaymentAPI {
  //캠페인 후원
  donation(donationData) {
    return tokenApi.post(`/payment/donate`, JSON.stringify(donationData));
  }
  // 내가 후원한 캠페인 조회
  mydonation() {
    return tokenApi.get(`/payment/my/donation`);
  }
  // OCR API 이용, 영수증 데이터 받아오기
  OCR(receipt) {
    return api.post(`/ocr`, JSON.stringify(receipt));
  }
  // 출금요청
  withdraw(campaignId, receipt) {
    return withdrawApi.post(`/payment/receipt/${campaignId}`, receipt);
  }
  getWithdraw(campaignId) {
    return tokenApi.get(`/payment/withdrawal/${campaignId}`);
  }
}

export default new PaymentAPI();
