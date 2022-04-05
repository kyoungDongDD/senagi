import { fileApi, ocrApi } from './index';
// import axios from 'axios';

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

// export async function OCR(receipt) {
//   try {
//     const response = await axios.post(
//       `https://sjzq3u7j26.apigw.ntruss.com/custom/v1/14843/0c3307a350bcc0e2b944ccdb8fc49c191fcd75425562e54c2570aa65f0b29b65/document/receipt`,
//       JSON.stringify(receipt),
//     );
//     return response.data;
//   } catch (err) {
//     console.log('Falie to fetch user:', err);
//     return 'Unknown';
//   }
// }

export default new PaymentAPI();
// export { OCR }
