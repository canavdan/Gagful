import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Header } from '../Header';
import { Footer } from '../Footer';
import './style.css';
import Category  from '../Category';
import { Search } from '../Search';

export class ProfileHome extends Component {
  constructor(props) {
    super(props);

    this.state = {};
  }

  render() {
    return (
      <>
        <Header />
        <div className="container">
          <div className="row">
            <div className="col-md-4">
              <Search />
              <Category />
            </div>
            <div className="col-md-8">
              <h1 className="my-4">
                Page Heading
                <small>Secondary Text</small>
              </h1>

              <ul className="pagination justify-content-center mb-4">
                <li className="page-item">
                  <a className="page-link" href="#">
                    &larr; Older
                  </a>
                </li>
                <li className="page-item disabled">
                  <a className="page-link" href="#">
                    Newer &rarr;
                  </a>
                </li>
              </ul>
            </div>
          </div>
        </div>
        <Footer />
      </>
    );
  }
}

const mapStateToProps = (state) => ({});

const mapDispatchToProps = {};

export default connect(mapStateToProps, mapDispatchToProps)(ProfileHome);
