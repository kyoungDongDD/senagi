import { createSlice } from '@reduxjs/toolkit';

// const initialStateValue = { isLogin: false, userInfo: null };
const initialStateValue = { isLogin: false, userInfo: null };

export const userSlice = createSlice({
  name: 'user',
  initialState: { value: initialStateValue },
  reducers: {
    // 로그인 토큰 정보 저장
    login: (state, action) => {
      state.value = action.payload;
    },
    // 로그아웃
    logout: (state) => {
      state.value = initialStateValue;
    },
    // checkLogin: (state) => {
    //   return state.value.isLogin;
    // },
    checkUserInfo: (state) => {
      return state.value.userInfo;
    },
    // 로그인 성공시 decode한 토큰의 유저 정보 세션에 저장
    authSuccess: (state, action) => {
      // console.log('state', state);
      // console.log('action', action);
      // console.log('state.value', state.value);
      // console.log('typeof state.value', typeof state.value);
      state.value.isLogin = true;
      state.value.userInfo = action.payload;
    },
    socialAuthSuccess: (state, action) => {
      // console.log('state', state);
      // console.log('action', action);
      // console.log('state.value', state.value);
      // console.log('typeof state.value', typeof state.value);
      const obj = JSON.parse(state.value); // string -> object 타입 변환
      const handler = {}; // handler : Prxy에서 trap들을 가지고 있는 Placeholder 객체 (??) 이해 못함
      state.value = new Proxy(obj, handler); // Proxy 타입으로 타입 변환
      // console.log('state.value', state.value);
      // console.log('typeof state.value', typeof state.value);
      state.value.isLogin = true;
      state.value.userInfo = action.payload;
    },
  },
});

export const { login, logout, authSuccess, socialAuthSuccess } = userSlice.actions;
export default userSlice.reducer;
