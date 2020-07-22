import React, { useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import {
  useParams,
} from 'react-router-dom';
import { getPosts } from '../../actions/postActions'

import Post from '../Post'

const PostCard = () => {
  const { postType } = useParams();
  const dispatch = useDispatch()
  const { post } = useSelector((state) => ({
    post: state.post,
  }))

  useEffect(() => {
    dispatch(getPosts(postType))
  }, [postType])

  const renderPosts = () => {
    if (post.isLoading) return <p>Loading posts...</p>
    if (post.errorMessage.length > 5) return <p>Unable to display posts.</p>
    return post.posts.map((post) => <Post key={post.id} post={post} />)
  }

  return (
    <>
      { renderPosts() }
    </>
  )
}
export default PostCard
