import { fileApi, ocrApi, api, tokenapi } from './index';

class PaymentAPI {
  //캠페인 후원
  donation(donationData) {
    return tokenapi.post(`/payment/donate`, JSON.stringify(donationData));
  }
  // 내가 후원한 캠페인 조회
  mydonation() {
    return tokenapi.get(`/payment/my/donation`);
  }
  // OCR API 이용, 영수증 데이터 받아오기
  OCR(receipt) {
    return ocrApi.post(`/document/receipt`, JSON.stringify(receipt));
  }
  // 출금요청
  withdraw(campaignId, receipt) {
    return fileApi.post(`/payment/receipt/${campaignId}`, receipt);
  }
}

export default new PaymentAPI();
