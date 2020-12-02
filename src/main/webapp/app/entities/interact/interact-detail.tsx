import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './interact.reducer';
import { IInteract } from 'app/shared/model/interact.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IInteractDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const InteractDetail = (props: IInteractDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { interactEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="nuriSocialApp.interact.detail.title">Interact</Translate> [<b>{interactEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="text">
              <Translate contentKey="nuriSocialApp.interact.text">Text</Translate>
            </span>
          </dt>
          <dd>{interactEntity.text}</dd>
          <dt>
            <span id="coin">
              <Translate contentKey="nuriSocialApp.interact.coin">Coin</Translate>
            </span>
          </dt>
          <dd>{interactEntity.coin}</dd>
          <dt>
            <span id="point">
              <Translate contentKey="nuriSocialApp.interact.point">Point</Translate>
            </span>
          </dt>
          <dd>{interactEntity.point}</dd>
          <dt>
            <span id="respect">
              <Translate contentKey="nuriSocialApp.interact.respect">Respect</Translate>
            </span>
          </dt>
          <dd>{interactEntity.respect}</dd>
          <dt>
            <span id="diss">
              <Translate contentKey="nuriSocialApp.interact.diss">Diss</Translate>
            </span>
          </dt>
          <dd>{interactEntity.diss}</dd>
          <dt>
            <span id="author">
              <Translate contentKey="nuriSocialApp.interact.author">Author</Translate>
            </span>
          </dt>
          <dd>{interactEntity.author}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="nuriSocialApp.interact.status">Status</Translate>
            </span>
          </dt>
          <dd>{interactEntity.status}</dd>
          <dt>
            <span id="created">
              <Translate contentKey="nuriSocialApp.interact.created">Created</Translate>
            </span>
          </dt>
          <dd>{interactEntity.created ? <TextFormat value={interactEntity.created} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="modified">
              <Translate contentKey="nuriSocialApp.interact.modified">Modified</Translate>
            </span>
          </dt>
          <dd>{interactEntity.modified ? <TextFormat value={interactEntity.modified} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <Translate contentKey="nuriSocialApp.interact.you">You</Translate>
          </dt>
          <dd>{interactEntity.youId ? interactEntity.youId : ''}</dd>
          <dt>
            <Translate contentKey="nuriSocialApp.interact.issue">Issue</Translate>
          </dt>
          <dd>{interactEntity.issueId ? interactEntity.issueId : ''}</dd>
          <dt>
            <Translate contentKey="nuriSocialApp.interact.parent">Parent</Translate>
          </dt>
          <dd>{interactEntity.parentId ? interactEntity.parentId : ''}</dd>
          <dt>
            <Translate contentKey="nuriSocialApp.interact.me">Me</Translate>
          </dt>
          <dd>{interactEntity.meId ? interactEntity.meId : ''}</dd>
        </dl>
        <Button tag={Link} to="/interact" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/interact/${interactEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ interact }: IRootState) => ({
  interactEntity: interact.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(InteractDetail);
