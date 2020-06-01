import React, { Component } from 'react';
import { connect } from 'react-redux';

export class PostCard extends Component {
  constructor(props) {
    super(props);

    this.state = {};
  }

  render() {
    return (
      <>
        <div className="card mb-4">
          <img
            className="card-img-top"
            src="http://placehold.it/750x300"
            alt="Card image cap"
          />
          <div className="card-body">
            <h2 className="card-title">Post Title</h2>
            <p className="card-text">
              Lorem ipsum dolor sit amet, consectetur adipisicing elit.
              Reiciendis aliquid atque, nulla? Quos cum ex quis soluta, a
              laboriosam. Dicta expedita corporis animi vero voluptate
              voluptatibus possimus, veniam magni quis!
            </p>
            <a href="#" className="btn btn-primary">
              Read More &rarr;
            </a>
          </div>
          <div className="card-footer text-muted">
            Posted on January 1, 2017 by
            <a href="#">Start Bootstrap</a>
          </div>
        </div>
        <div className="card mb-4">
          <img
            className="card-img-top"
            src="http://placehold.it/750x300"
            alt="Card image cap"
          />
          <div className="card-body">
            <h2 className="card-title">Post Title</h2>
            <p className="card-text">
              Lorem ipsum dolor sit amet, consectetur adipisicing elit.
              Reiciendis aliquid atque, nulla? Quos cum ex quis soluta, a
              laboriosam. Dicta expedita corporis animi vero voluptate
              voluptatibus possimus, veniam magni quis!
            </p>
            <a href="#" className="btn btn-primary">
              Read More &rarr;
            </a>
          </div>
          <div className="card-footer text-muted">
            Posted on January 1, 2017 by
            <a href="#">Start Bootstrap</a>
          </div>
        </div>
      </>
    );
  }
}

const mapStateToProps = (state) => ({});

const mapDispatchToProps = {};

export default connect(mapStateToProps, mapDispatchToProps)(PostCard);
