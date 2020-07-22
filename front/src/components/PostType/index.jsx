import React from 'react'
import { Image } from 'react-bootstrap'
import { Link } from "react-router-dom";

import './style.css'
import hotIcon from '../../styles/pictures/hot-icon.jpg'
import trendIcon from '../../styles/pictures/trending.jpg'
import freshIcon from '../../styles/pictures/clock.jpg'

const PostType = () => (
  <div className="card">
    <div className="card-body">
      <ul className="listrap">
        <li>
          <Link to="/">
          <div className="listrap">
            <Image
              src={hotIcon}
              width="60px"
              height="60px"
              roundedCircle
            />
            <strong>Populer Posts</strong>
          </div>
        </Link>
        </li>
        <li>
        <Link to="/trending">
          <div className="listrap">
            <Image
              src={trendIcon}
              width="60px"
              height="60px"
              roundedCircle
            />
            <strong>Trending Posts</strong>
          </div>
          </Link>
        </li>
        <li>
        <Link to="/fresh">
          <div className="listrap">
            <Image
              src={freshIcon}
              width="60px"
              height="60px"
              roundedCircle
            />
            <strong>Fresh Posts</strong>
          </div>
          </Link>
        </li>
      </ul>
    </div>
  </div>
)

export default PostType
