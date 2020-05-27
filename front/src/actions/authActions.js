import {
  CREATE_USER_SUCCESS,
  CREATE_USER_FAILURE,
  LOGIN_USER_SUCCESS,
  LOG_OUT,
  SESSION_ERROR,
  SESSION_LOADING,
  LOGIN_USER_FAILURE,
} from './types';

export const createUserSuccess = (token) => ({
  type: CREATE_USER_SUCCESS,
  payload: token,
});

export const createUserFail = (error) => ({
  type: CREATE_USER_FAILURE,
  payload: error,
});

export const loginUserSuccess = (response) => ({
  type: LOGIN_USER_SUCCESS,
  payload: response,
});

export const loginUserFail = (error) => ({
  type: LOGIN_USER_FAILURE,
  payload: error,
});

export const loginOut = () => ({
  type: LOG_OUT,
});

export const sessionError = (error) => ({
  type: SESSION_ERROR,
  payload: error,
});
export const sessionLoading = () => ({
  type: SESSION_LOADING,
});

export const dispatchLogin = () => ({ type: LOGIN_USER_SUCCESS });

export const createUser = (email, password) => async (dispatch) => {
  dispatch(sessionLoading());
  const response = await fetch('http://localhost:8080/api/v1/member/sign-up', {
    method: 'POST',
    headers: { 'content-type': 'application/json' },
    body: JSON.stringify({ email, password }),
  });

  if (response.ok) {
    createUserSuccess(response.JWTtoken);
  } else {
    const errMessage = await response.text();
    createUserFail(errMessage);
  }
};
export const loginUser = (email, password) => async (dispatch) => {
  dispatch(sessionLoading());
  const response = await fetch('http://localhost:8080/api/v1/member/login', {
    method: 'POST',
    headers: { 'content-type': 'application/json' },
    body: JSON.stringify({ email, password }),
  });

  if (response.ok) {
    createUserSuccess(response.JWTtoken);
  } else {
    const errMessage = await response.text();
    createUserFail(errMessage);
  }
};
export const logoutUser = () => (dispatch) => {
  dispatch(sessionLoading());
  loginOut();
};
