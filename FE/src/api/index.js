import axios from 'axios';

const api = axios.create({
  // baseURL: 'http://localhost:8080/api/',
  // baseURL: 'https://84c8d203-4698-4ccf-a301-71d6e01c0494.mock.pstmn.io',
  baseURL: 'https://j6b105.p.ssafy.io/api/',
  headers: {
    'Content-Type': 'application/json',
  },
});

const fileApi = axios.create({
  baseURL: 'https://j6b105.p.ssafy.io/api/',
  headers: {
    'Content-Type': 'multipart/form-data',
  },
});

const ocrApi = axios.create({
  // baseURL:
  //   'https://sjzq3u7j26.apigw.ntruss.com/custom/v1/14843/0c3307a350bcc0e2b944ccdb8fc49c191fcd75425562e54c2570aa65f0b29b65/',
  baseURL: 'https://sjzq3u7j26.apigw.ntruss.com/custom/v1/',
  // baseURL:
  // 'https://cors.bridged.cc/http://clovaocr-api-kr.ncloud.com/external-private/v1/14843/0c3307a350bcc0e2b944ccdb8fc49c191fcd75425562e54c2570aa65f0b29b65/',
  // baseURL: '/custom/v1/14843/0c3307a350bcc0e2b944ccdb8fc49c191fcd75425562e54c2570aa65f0b29b65/',
  headers: {
    'Content-Type': 'application/json',
    'X-OCR-SECRET': 'amZPVUZxdG1RempqR056UG9TTnBUZVhGV2ZSS2dUQWI=',
  },
});

api.interceptors.request.use(function (config) {
  const user = localStorage.getItem('user');
  if (!user) {
    config.headers['accessToken'] = null;
    config.headers['refreshToken'] = null;
    return config;
  }
  const { accessToken, refreshToken } = JSON.parse(user);
  config.headers['accessToken'] = accessToken;
  config.headers['refreshToken'] = refreshToken;
  return config;
});

api.interceptors.response.use(
  function (response) {
    return response;
  },
  async function (error) {
    if (error.response && error.response.status === 403) {
      try {
        const originalRequest = error.config;
        const data = await api.get('auth/login/supporter');
        if (data) {
          const { accessToken, refreshToken } = data.data;
          localStorage.removeItem('user');
          localStorage.setItem('user', JSON.stringify(data.data, ['accessToken', 'refreshToken']));
          originalRequest.headers['accessToken'] = accessToken;
          originalRequest.headers['refreshToken'] = refreshToken;
          return await api.request(originalRequest);
        }
      } catch (error) {
        localStorage.removeItem('user');
        console.log(error);
      }
      return Promise.reject(error);
    }
    return Promise.reject(error);
  },
);

export { api, fileApi, ocrApi };
