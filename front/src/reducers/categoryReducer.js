import {
  FETCHING_CATEGORY_REQUEST,
  FETCHING_CATEGORY_SUCCESS,
  FETCHING_CATEGORY_FAILURE,
} from '../actions/types'

const initialState = {
  isLoading: true,
  categories: [],
  errorMessage: '',
}

const categoryReducer = (state = initialState, { type, payload }) => {
  switch (type) {
    case FETCHING_CATEGORY_REQUEST:
      return { ...state, isLoading: true }
    case FETCHING_CATEGORY_FAILURE:
      return { ...state, isLoading: false, errorMessage: payload }
    case FETCHING_CATEGORY_SUCCESS:
      return { ...state, isLoading: false, categories: payload }
    default:
      return state
  }
}

export default categoryReducer
