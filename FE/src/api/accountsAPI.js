import api from './index';

class AccountsAPI {
  // 회원가입
  signUp(accountData) {
    return api.post(`/api/auth/signup/supporter`, JSON.stringify(accountData));
  }

  // 이메일 중복확인
  checkEmail(userId) {
    return api.get(`/api/auth/duplicate/principal?principal=${userId}`);
  }

  // 닉네임 중복확인
  checkName(name) {
    return api.get(`/api/auth/duplicate/name?name=${name}`);
  }

  // 후원자 로그인
  supporterLogin(accountData) {
    return api.post(`/api/auth/login/supporter`, JSON.stringify(accountData));
  }

  // 보호소 로그인
  shelterLogin(accountData) {
    return api.post(`/api/auth/login/shelter`, JSON.stringify(accountData));
  }
}

export default new AccountsAPI();
