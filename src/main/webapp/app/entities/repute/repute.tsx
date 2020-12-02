import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './repute.reducer';
import { IRepute } from 'app/shared/model/repute.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IReputeProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Repute = (props: IReputeProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { reputeList, match, loading } = props;
  return (
    <div>
      <h2 id="repute-heading">
        <Translate contentKey="nuriSocialApp.repute.home.title">Reputes</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="nuriSocialApp.repute.home.createLabel">Create new Repute</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {reputeList && reputeList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="nuriSocialApp.repute.description">Description</Translate>
                </th>
                <th>
                  <Translate contentKey="nuriSocialApp.repute.grade">Grade</Translate>
                </th>
                <th>
                  <Translate contentKey="nuriSocialApp.repute.credit">Credit</Translate>
                </th>
                <th>
                  <Translate contentKey="nuriSocialApp.repute.status">Status</Translate>
                </th>
                <th>
                  <Translate contentKey="nuriSocialApp.repute.created">Created</Translate>
                </th>
                <th>
                  <Translate contentKey="nuriSocialApp.repute.modified">Modified</Translate>
                </th>
                <th>
                  <Translate contentKey="nuriSocialApp.repute.you">You</Translate>
                </th>
                <th>
                  <Translate contentKey="nuriSocialApp.repute.me">Me</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {reputeList.map((repute, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${repute.id}`} color="link" size="sm">
                      {repute.id}
                    </Button>
                  </td>
                  <td>{repute.description}</td>
                  <td>{repute.grade}</td>
                  <td>{repute.credit}</td>
                  <td>
                    <Translate contentKey={`nuriSocialApp.ReputeStatus.${repute.status}`} />
                  </td>
                  <td>{repute.created ? <TextFormat type="date" value={repute.created} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{repute.modified ? <TextFormat type="date" value={repute.modified} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{repute.youId ? <Link to={`avatar/${repute.youId}`}>{repute.youId}</Link> : ''}</td>
                  <td>{repute.meId ? <Link to={`avatar/${repute.meId}`}>{repute.meId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${repute.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${repute.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${repute.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="nuriSocialApp.repute.home.notFound">No Reputes found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ repute }: IRootState) => ({
  reputeList: repute.entities,
  loading: repute.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Repute);
