import {
  FETCHING_CATEGORY_REQUEST,
  FETCHING_CATEGORY_SUCCESS,
  FETCHING_CATEGORY_FAILURE,
} from './types'
import { get } from '../api/requestUtil'

export const getCategoriesFailure = (errorMessage) => ({
  type: FETCHING_CATEGORY_FAILURE,
  payload: errorMessage,
})

export const getCategories = () => (dispatch) => {
  dispatch({ type: FETCHING_CATEGORY_REQUEST })
  get('/public/categories')
    .then((response) => {
      if (response.data) {
        const categories = response.data.list;
        dispatch({
          type: FETCHING_CATEGORY_SUCCESS,
          payload: categories,
        })
      } else { dispatch(getCategoriesFailure(response.message || 'Error occured')); }
    }).catch((err) => {
      dispatch(getCategoriesFailure(err.message));
    })
}
