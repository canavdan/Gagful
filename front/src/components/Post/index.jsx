/* eslint-disable react/prop-types */
import React from 'react'
import { Image } from 'react-bootstrap'
import commentIcon from '../../styles/icon/commentIcon.png'
import downArrowIcon from '../../styles/icon/downArrowIcon.png'
import upArrowIcon from '../../styles/icon/upArrowIcon.png'
import './style.css'

const Post = ({ post }) => {
  const pointNumber = post.votes[1].count - post.votes[0].count < 0 ? 0 : post.votes[1].count - post.votes[0].count
  const commentsNumber = 5
  return (
    <>
      <div className="card card-cascade">
        <div className="row">
          <div className="col-sm-3">
            <a href="http:">
              <i className="fa fa-comment fa-fw" aria-hidden="true" />
&nbsp;
              {' '}
              {post.categoryName}
            </a>
          </div>
          <div className="col-sm-3">
            <h6>{post.createdDate}</h6>
          </div>
        </div>
        <br />
        <h2 className="card-title">
          <strong>{post.title}</strong>
        </h2>

        <div className="view view-cascade overlay">
          {post.image && (
          <img
            src={`data:image/jpeg;base64,${post.image}`}
            alt="Gag"
            className="card-img-top"
          />
          )}
          <div className="mask rgba-white-slight" />
        </div>
        <div className="counter-block m-t-5 p-10">
          <div>
            <h6>
              {pointNumber}
              {' '}
              Points -
              {' '}
              {commentsNumber}
              {' '}
              Comments
            </h6>
          </div>
          <div className="row">
            <div className="col-1">
              <div>
                <button type="button" className="btn btn-xlarge">
                  {' '}
                  <Image
                    src={upArrowIcon}
                    width="60px"
                    height="60px"
                  />
                </button>
              </div>
            </div>
            <div className="col-1">
              <div>
                <button type="button" className="btn btn-xlarge">
                  {' '}
                  <Image
                    src={downArrowIcon}
                    width="60px"
                    height="60px"
                  />
                </button>
              </div>
            </div>
            <div className="col-1">
              <div>
                <button type="button" className="btn btn-xlarge">
                  {' '}
                  <Image
                    src={commentIcon}
                    width="60px"
                    height="60px"
                  />
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  )
}
export default Post
