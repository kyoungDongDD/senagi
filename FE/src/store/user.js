import { createSlice } from '@reduxjs/toolkit';

const initialStateValue = { isLogin: false, userInfo: null };

export const userSlice = createSlice({
  name: 'user',
  initialState: { value: initialStateValue },
  reducers: {
    // // 로그인 토큰 정보 저장
    // login: (state, action) => {
    //   state.value = action.payload;
    // },
    // 로그아웃
    logout: (state) => {
      state.value = initialStateValue;
    },
    // checkLogin: (state) => {
    //   return state.isLogin;
    // },
    // checkUserInfo: (state) => {
    //   return state.userInfo;
    // },
    // 로그인 성공시 decode한 토큰의 유저 정보 세션에 저장
    authSuccess: (state, action) => {
      state.value.isLogin = true;
      state.value.userInfo = action.payload;
    },
  },
});

export const { login, logout, authSuccess } = userSlice.actions;
export default userSlice.reducer;
