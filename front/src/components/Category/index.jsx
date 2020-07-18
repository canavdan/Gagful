import React, { useEffect } from "react";
import { connect } from "react-redux";
import { getCategories } from "../../actions/categoryActions";
import {Image,Row,Col} from 'react-bootstrap'
import "./style.css"
const Category = ({ category, getCategories }) => {
  useEffect(() => {
    getCategories();
  }, []);

  const renderCategories = () => {
    if (category.isLoading) return <p>Loading categories...</p>;
    if (category.errorMessage.length > 5)
      return <p>Unable to display categories.</p>;
    return category.categories.map(category => (
      <Row>
        <Col>
      <li>
         <div className="listrap">
                    
        {category.icon ? (
          <Image
            src={`data:image/jpeg;base64,${category.icon}`}
            width="60px"
            height="60px"
            roundedCircle
          />
        ) : (
          <Image src="http://lorempixel.com/60/60/people/?v=1" roundedCircle />
      
        )}
               
                <strong>{category.name}</strong>
                </div>
      </li>
      </Col>
      </Row>
    ));
  };

  return (
    <div className="card">
      <h5 className="card-header">Categories</h5>
      <div className="card-body">
        <div className="row">
            <ul className="listrap">{renderCategories()}</ul>
        </div>
      </div>
    </div>
  );
};

const mapStateToProps = state => ({ category: state.category });

const mapDispatchToProps = { getCategories };

export default connect(mapStateToProps, mapDispatchToProps)(Category);
