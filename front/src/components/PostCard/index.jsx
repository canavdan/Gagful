import React, { useEffect, useState } from 'react'
import { connect } from 'react-redux'
import { getNewPosts, getPopulerPosts, getTrendingPosts } from '../../actions/postActions'
import  Post  from '../Post'

const PostCard = ({ post, getNewPosts }) => {
  const [test, setTest] = useState([])
  useEffect(() => {
    getNewPosts()
  }, [])

 
  const renderPosts = () => {
    
    if (post.isLoading) return <p>Loading posts...</p>
    if (post.errorMessage.length>5) return <p>Unable to display posts.</p>
    return post.posts.map((post) => <Post key={post.id} post={post} />)
  }

  return (
    <>
    { renderPosts() }
    </>
  )
}

const mapStateToProps = (state) => ({ post: state.post });

const mapDispatchToProps = { getNewPosts };

export default connect(mapStateToProps, mapDispatchToProps)(PostCard);
