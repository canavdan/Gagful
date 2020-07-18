import React, { useEffect } from "react";
import { connect } from "react-redux";
import { getCategories } from "../../actions/categoryActions";
import { Image } from "react-bootstrap";
import "./style.css";
import hotIcon from '../../styles/pictures/hot-icon.jpg';
import trendIcon from '../../styles/pictures/trending.jpg';
import freshIcon from '../../styles/pictures/clock.jpg';
const PostType = () => {
  return (
    <div className="card">
      <div className="card-body">
        <ul className="listrap">
          <li>
            <div className="listrap">
              <Image
                src={hotIcon}
                width="60px"
                height="60px"
                roundedCircle
              />
              <strong>Populer Posts</strong>
            </div>
          </li>
          <li>
            <div className="listrap">
              <Image
                src={trendIcon}
                width="60px"
                height="60px"
                roundedCircle
              />
              <strong>Trending Posts</strong>
            </div>
          </li>
          <li>
            <div className="listrap">
              <Image
                src={freshIcon}
                width="60px"
                height="60px"
                roundedCircle
              />
              <strong>Fresh Posts</strong>
            </div>
          </li>
        </ul>
      </div>
    </div>
  );
};

export default connect()(PostType);
