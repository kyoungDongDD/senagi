import { configureStore } from '@reduxjs/toolkit';
import counterReducer from '../components/pages/counterSlice';

export default configureStore({
  reducer: {
    counter: counterReducer,
  },
});
