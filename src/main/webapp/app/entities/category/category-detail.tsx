import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './category.reducer';
import { ICategory } from 'app/shared/model/category.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICategoryDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CategoryDetail = (props: ICategoryDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { categoryEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="nuriSocialApp.category.detail.title">Category</Translate> [<b>{categoryEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="path">
              <Translate contentKey="nuriSocialApp.category.path">Path</Translate>
            </span>
          </dt>
          <dd>{categoryEntity.path}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="nuriSocialApp.category.name">Name</Translate>
            </span>
          </dt>
          <dd>{categoryEntity.name}</dd>
          <dt>
            <span id="icon">
              <Translate contentKey="nuriSocialApp.category.icon">Icon</Translate>
            </span>
          </dt>
          <dd>{categoryEntity.icon}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="nuriSocialApp.category.description">Description</Translate>
            </span>
          </dt>
          <dd>{categoryEntity.description}</dd>
          <dt>
            <span id="hitAndSort">
              <Translate contentKey="nuriSocialApp.category.hitAndSort">Hit And Sort</Translate>
            </span>
          </dt>
          <dd>{categoryEntity.hitAndSort}</dd>
          <dt>
            <span id="respect">
              <Translate contentKey="nuriSocialApp.category.respect">Respect</Translate>
            </span>
          </dt>
          <dd>{categoryEntity.respect}</dd>
          <dt>
            <span id="diss">
              <Translate contentKey="nuriSocialApp.category.diss">Diss</Translate>
            </span>
          </dt>
          <dd>{categoryEntity.diss}</dd>
          <dt>
            <span id="join">
              <Translate contentKey="nuriSocialApp.category.join">Join</Translate>
            </span>
          </dt>
          <dd>{categoryEntity.join}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="nuriSocialApp.category.status">Status</Translate>
            </span>
          </dt>
          <dd>{categoryEntity.status}</dd>
          <dt>
            <span id="created">
              <Translate contentKey="nuriSocialApp.category.created">Created</Translate>
            </span>
          </dt>
          <dd>{categoryEntity.created ? <TextFormat value={categoryEntity.created} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="modified">
              <Translate contentKey="nuriSocialApp.category.modified">Modified</Translate>
            </span>
          </dt>
          <dd>{categoryEntity.modified ? <TextFormat value={categoryEntity.modified} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <Translate contentKey="nuriSocialApp.category.me">Me</Translate>
          </dt>
          <dd>{categoryEntity.meId ? categoryEntity.meId : ''}</dd>
        </dl>
        <Button tag={Link} to="/category" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/category/${categoryEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ category }: IRootState) => ({
  categoryEntity: category.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CategoryDetail);
