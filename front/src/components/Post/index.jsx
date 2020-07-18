import React, { useEffect } from "react";
import { Button, Tooltip } from "antd";
import {
  UpCircleTwoTone,
  ArrowDownOutlined,
  SettingOutlined
} from "@ant-design/icons";
import "./style.css";

const Post = ({ post }) => {
  useEffect(() => {
    console.log("a");
  }, []);

  return (
    <>
      <div className="card card-cascade">
        <div className="row">
          <div className="col-sm-3">
            <a href="#">
              <i className="fa fa-comment fa-fw" aria-hidden="true"></i>&nbsp;{" "}
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
              alt="Card image cap"
              className="card-img-top"
            />
          )}
          <a>
            <div className="mask rgba-white-slight"></div>
          </a>
        </div>
        <div className="bg-c-blue counter-block m-t-10 p-20">
          <div className="row">
            <div className="col-4">
              <i className="fa fa-comment fa-2x"></i>
              <h3>1256</h3>
            </div>
            <div className="col-4">
              <i className="fa fa-long-arrow-up fa-2x"></i>
              <h3> {post.votes[1].count} </h3>
            </div>
            <div className="col-4">
              <i className="fa fa-long-arrow-down fa-2x"></i>
              <h3> {post.votes[1].count} </h3>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default Post;
