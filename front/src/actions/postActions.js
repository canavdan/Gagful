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

export const getPopulerPosts = () => (dispatch) => {
  get('/public/posts/fresh')
    .then((response) => {
      const posts = response.list;
      alert(response)
      dispatch({
        type: FETCHING_POPULER_POST_REQUEST,
        payload: posts,
      })
    })
}

export const getTrendingPosts = () => (dispatch) => {
  get('/public/posts/fresh')
    .then((response) => {
      const posts = response.data;
      dispatch({
        type: FETCHING_TRENDING_POST_REQUEST,
        payload: posts,
      })
    })
}

export const getNewPosts = () => (dispatch) => {
  dispatch({type: FETCHING_NEW_POST_REQUEST})
  get('/public/posts/fresh')
    .then((response) => {
      console.log(response)
      if(response.data){
        const posts = response.data.list;
        dispatch({
          type: FETCHING_POST_SUCCESS,
          payload: posts,
        })
      }
      else
      dispatch(getPostsFailure(response.message || "Error occured"));
    }).catch(function(err) {
      dispatch(getPostsFailure(err.message));
    });
}
