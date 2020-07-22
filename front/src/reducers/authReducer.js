import {
  CREATE_USER_SUCCESS,
  CREATE_USER_FAILURE,
  LOGIN_USER_SUCCESS,
  LOGIN_USER_FAILURE,
  LOG_OUT,
  SESSION_ERROR,
  SESSION_LOADING,
} from '../actions/types'

const initialState = {
  loggedIn: false,
  loggingOut: false,
  loading: false,
  error: null,
  iscreatedUser: false,
  user: {},
}

const authReducer = (state = initialState, action) => {
  switch (action.type) {
    case LOGIN_USER_SUCCESS:
      return {
        ...state, loading: false, loggedIn: true, user: action.payload,
      }
    case LOGIN_USER_FAILURE: {
      return {
        ...state,
        loading: false,
        loggedIn: false,
        error: action.payload,
      }
    }
    case LOG_OUT:
      return { loggingOut: true, loading: false };
    case CREATE_USER_SUCCESS:
      return { ...state, loading: false, iscreatedUser: true };
    case CREATE_USER_FAILURE:
      return {
        ...state,
        error: action.payload,
        iscreatedUser: false,
        loading: false,
      }
    case SESSION_LOADING:
      return { ...state, loading: true, error: null };
    case SESSION_ERROR:
      return { ...state, loading: false, error: action.payload };
    default:
      return state;
  }
}
export default authReducer
