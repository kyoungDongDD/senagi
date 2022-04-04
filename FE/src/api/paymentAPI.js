import { fileApi, ocrApi } from './index';

class PaymentAPI {
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
