import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './category.reducer';
import { ICategory } from 'app/shared/model/category.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICategoryProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Category = (props: ICategoryProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { categoryList, match, loading } = props;
  return (
    <div>
      <h2 id="category-heading">
        <Translate contentKey="nuriSocialApp.category.home.title">Categories</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="nuriSocialApp.category.home.createLabel">Create new Category</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {categoryList && categoryList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="nuriSocialApp.category.path">Path</Translate>
                </th>
                <th>
                  <Translate contentKey="nuriSocialApp.category.name">Name</Translate>
                </th>
                <th>
                  <Translate contentKey="nuriSocialApp.category.icon">Icon</Translate>
                </th>
                <th>
                  <Translate contentKey="nuriSocialApp.category.description">Description</Translate>
                </th>
                <th>
                  <Translate contentKey="nuriSocialApp.category.hitAndSort">Hit And Sort</Translate>
                </th>
                <th>
                  <Translate contentKey="nuriSocialApp.category.respect">Respect</Translate>
                </th>
                <th>
                  <Translate contentKey="nuriSocialApp.category.diss">Diss</Translate>
                </th>
                <th>
                  <Translate contentKey="nuriSocialApp.category.join">Join</Translate>
                </th>
                <th>
                  <Translate contentKey="nuriSocialApp.category.status">Status</Translate>
                </th>
                <th>
                  <Translate contentKey="nuriSocialApp.category.created">Created</Translate>
                </th>
                <th>
                  <Translate contentKey="nuriSocialApp.category.modified">Modified</Translate>
                </th>
                <th>
                  <Translate contentKey="nuriSocialApp.category.me">Me</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {categoryList.map((category, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${category.id}`} color="link" size="sm">
                      {category.id}
                    </Button>
                  </td>
                  <td>{category.path}</td>
                  <td>{category.name}</td>
                  <td>{category.icon}</td>
                  <td>{category.description}</td>
                  <td>{category.hitAndSort}</td>
                  <td>{category.respect}</td>
                  <td>{category.diss}</td>
                  <td>{category.join}</td>
                  <td>
                    <Translate contentKey={`nuriSocialApp.CategoryStatus.${category.status}`} />
                  </td>
                  <td>{category.created ? <TextFormat type="date" value={category.created} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{category.modified ? <TextFormat type="date" value={category.modified} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{category.meId ? <Link to={`avatar/${category.meId}`}>{category.meId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${category.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${category.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${category.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="nuriSocialApp.category.home.notFound">No Categories found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ category }: IRootState) => ({
  categoryList: category.entities,
  loading: category.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Category);
