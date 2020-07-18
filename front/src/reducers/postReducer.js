import {
  FETCHING_POST_SUCCESS,
  FETCHING_POST_FAILURE,
  FETCHING_TRENDING_POST_REQUEST,
  FETCHING_NEW_POST_REQUEST,
  FETCHING_POPULER_POST_REQUEST,
  ADD_POST,
  ADD_POST_FAILURE,
  UPDATE_POST,
  UPDATE_POST_FAILURE,
  DELETE_POST,
  DELETE_POST_FAILURE,
} from '../actions/types'

const initialState = {
  isLoading: true,
  posts: [],
  errorMessage: '',
}

const postReducer = (state = initialState, { type, payload }) => {
  switch (type) {
    case FETCHING_POPULER_POST_REQUEST:
      return { ...state, isLoading: true }
    case FETCHING_NEW_POST_REQUEST:
      return { ...state, isLoading: true }
    case FETCHING_TRENDING_POST_REQUEST:
      return { ...state, isLoading: true }
    case FETCHING_POST_SUCCESS:
      return { ...state, isLoading: false, posts: payload }
    case FETCHING_POST_FAILURE:
      return { ...state, isLoading: false, errorMessage: payload }
    case ADD_POST:
      return { ...state, errorMessage: '', posts: { ...state.posts, payload } }
    case ADD_POST_FAILURE:
      return { ...state, errorMessage: payload }
    case UPDATE_POST_FAILURE:
      return { ...state, errorMessage: payload }
    case DELETE_POST_FAILURE:
      return { ...state, errorMessage: payload }
    case DELETE_POST:
      return { ...state, errorMessage: '' }
    case UPDATE_POST:
      return { ...state, errorMessage: '' }
    default:
      return state
  }
}

export default postReducer
