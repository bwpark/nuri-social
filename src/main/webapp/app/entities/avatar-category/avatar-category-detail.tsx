import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './avatar-category.reducer';
import { IAvatarCategory } from 'app/shared/model/avatar-category.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAvatarCategoryDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AvatarCategoryDetail = (props: IAvatarCategoryDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { avatarCategoryEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="nuriSocialApp.avatarCategory.detail.title">AvatarCategory</Translate> [<b>{avatarCategoryEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="path">
              <Translate contentKey="nuriSocialApp.avatarCategory.path">Path</Translate>
            </span>
          </dt>
          <dd>{avatarCategoryEntity.path}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="nuriSocialApp.avatarCategory.name">Name</Translate>
            </span>
          </dt>
          <dd>{avatarCategoryEntity.name}</dd>
          <dt>
            <span id="icon">
              <Translate contentKey="nuriSocialApp.avatarCategory.icon">Icon</Translate>
            </span>
          </dt>
          <dd>{avatarCategoryEntity.icon}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="nuriSocialApp.avatarCategory.description">Description</Translate>
            </span>
          </dt>
          <dd>{avatarCategoryEntity.description}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="nuriSocialApp.avatarCategory.status">Status</Translate>
            </span>
          </dt>
          <dd>{avatarCategoryEntity.status}</dd>
          <dt>
            <span id="created">
              <Translate contentKey="nuriSocialApp.avatarCategory.created">Created</Translate>
            </span>
          </dt>
          <dd>
            {avatarCategoryEntity.created ? <TextFormat value={avatarCategoryEntity.created} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="modified">
              <Translate contentKey="nuriSocialApp.avatarCategory.modified">Modified</Translate>
            </span>
          </dt>
          <dd>
            {avatarCategoryEntity.modified ? (
              <TextFormat value={avatarCategoryEntity.modified} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
        </dl>
        <Button tag={Link} to="/avatar-category" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/avatar-category/${avatarCategoryEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ avatarCategory }: IRootState) => ({
  avatarCategoryEntity: avatarCategory.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AvatarCategoryDetail);
