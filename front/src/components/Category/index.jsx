import React, { useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { Image, Row, Col } from 'react-bootstrap'
import { Link } from 'react-router-dom'
import { getCategories } from '../../actions/categoryActions'
import './style.css'

const Category = () => {
  const dispatch = useDispatch();
  const { category } = useSelector((state) => ({
    category: state.category,
  }))
  useEffect(() => {
    dispatch(getCategories());
  }, [])

  const renderCategories = () => {
    if (category.isLoading) return <p>Loading categories...</p>;
    if (category.errorMessage.length > 5) { return <p>Unable to display categories.</p>; }
    return category.categories.map((categoryMap) => (
      <Row>
        <Col>
          <li>
            <Link to={`/c/${categoryMap.name}`}>
              <div className="listrap">
                {categoryMap.icon ? (
                  <Image
                    src={`data:image/jpeg;base64,${categoryMap.icon}`}
                    width="60px"
                    height="60px"
                    roundedCircle
                  />
                ) : (
                  <Image
                    src="http://lorempixel.com/60/60/people/?v=1"
                    roundedCircle
                  />
                )}
                <strong>{categoryMap.name}</strong>
              </div>
            </Link>
          </li>
        </Col>
      </Row>
    ))
  }

  return (
    <div className="card">
      <h5 className="card-header">Categories</h5>
      <div className="card-body">
        <div className="row">
          <ul className="listrap">{renderCategories()}</ul>
        </div>
      </div>
    </div>
  )
}

export default Category
