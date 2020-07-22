import {
  FETCHING_POST_REQUEST,
  FETCHING_POST_SUCCESS,
  FETCHING_POST_FAILURE,
  FETCHING_TRENDING_POST_REQUEST,
  FETCHING_NEW_POST_REQUEST,
  FETCHING_POPULER_POST_REQUEST,
} from './types'
import { get } from '../api/requestUtil'

export const getPostsFailure = (errorMessage) => ({
  type: FETCHING_POST_FAILURE,
  payload: errorMessage,
})

export const getPosts = (postType = 'popular') => (dispatch) => {
  dispatch({ type: FETCHING_POST_REQUEST })
  get(`/public/posts/${postType}`)
    .then((response) => {
      if (response.data.success) {
        const posts = response.data.list;
        dispatch({
          type: FETCHING_POST_SUCCESS,
          payload: posts,
        })
      } else { dispatch(getPostsFailure(response.message || 'Error occured')); }
    }).catch((err) => {
      dispatch(getPostsFailure(err.message));
    })
}

export const getPostsByCategory = (categoryName = 'Funny') => (dispatch) => {
  dispatch({ type: FETCHING_POST_REQUEST })
  get(`/public/posts/category/${categoryName}`)
    .then((response) => {
      if (response.data.success) {
        const posts = response.data.list;
        dispatch({
          type: FETCHING_POST_SUCCESS,
          payload: posts,
        })
      } else { dispatch(getPostsFailure(response.message || 'Error occured')); }
    }).catch((err) => {
      dispatch(getPostsFailure(err.message));
    })
}

export const getPopulerPosts = () => (dispatch) => {
  dispatch({ type: FETCHING_POPULER_POST_REQUEST })
  get('/public/posts/popular')
    .then((response) => {
      const posts = response.data.list;
      dispatch({
        type: FETCHING_POST_SUCCESS,
        payload: posts,
      })
    })
}

export const getTrendingPosts = () => (dispatch) => {
  dispatch({ type: FETCHING_TRENDING_POST_REQUEST })
  get('/public/posts/trending')
    .then((response) => {
      const posts = response.data.list;
      dispatch({
        type: FETCHING_POST_SUCCESS,
        payload: posts,
      })
    })
}

export const getNewPosts = () => (dispatch) => {
  dispatch({ type: FETCHING_NEW_POST_REQUEST })
  get('/public/posts/fresh')
    .then((response) => {
      if (response.data.success) {
        const posts = response.data.list;
        dispatch({
          type: FETCHING_POST_SUCCESS,
          payload: posts,
        })
      } else { dispatch(getPostsFailure(response.message || 'Error occured')); }
    }).catch((err) => {
      dispatch(getPostsFailure(err.message));
    })
}
