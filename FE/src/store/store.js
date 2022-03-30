import { combineReducers } from 'redux';
import { configureStore } from '@reduxjs/toolkit';
import { persistReducer, persistStore } from 'redux-persist';
import storageSession from 'redux-persist/lib/storage/session';  // session에 저장할 때는 storageSession, localStorage에 저장할 때는 storage import
import thunk from 'redux-thunk';
import userReducer from './user';

// combineReducers 내부에 새로운 reducer들 추가하기
const reducers = combineReducers({
  user: userReducer,
});

const persistConfig = {
  key: 'root', // 데이터의 저장 위치 : reducer의 'root' 지점부터 데이터를 저장한다.
  storage: storageSession,  // 세션 스토리지에 저장
  // whitelist: ["user"]   // whitelist : 원하는 reducer만 포지티브 적용 <-> blacklist : 네거티브 적용
}

// persistedReducer를 통해 persistConfig와 reducer연결
const persistedReducer = persistReducer(persistConfig, reducers);

// export const store = configureStore({ reducer: persistedReducer, middleware: middlewares });

export const store = configureStore({
  reducer: persistedReducer,
  devTools: process.env.NODE_ENV !== 'production',
  middleware: [thunk],
});

// 새로고침을 해도 지속 될 persistStore
export const persistor = persistStore(store);

export default { store, persistor };