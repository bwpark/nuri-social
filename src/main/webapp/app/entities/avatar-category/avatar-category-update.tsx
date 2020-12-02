import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './avatar-category.reducer';
import { IAvatarCategory } from 'app/shared/model/avatar-category.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IAvatarCategoryUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AvatarCategoryUpdate = (props: IAvatarCategoryUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { avatarCategoryEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/avatar-category');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    values.created = convertDateTimeToServer(values.created);
    values.modified = convertDateTimeToServer(values.modified);

    if (errors.length === 0) {
      const entity = {
        ...avatarCategoryEntity,
        ...values,
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="nuriSocialApp.avatarCategory.home.createOrEditLabel">
            <Translate contentKey="nuriSocialApp.avatarCategory.home.createOrEditLabel">Create or edit a AvatarCategory</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : avatarCategoryEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="avatar-category-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="avatar-category-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="pathLabel" for="avatar-category-path">
                  <Translate contentKey="nuriSocialApp.avatarCategory.path">Path</Translate>
                </Label>
                <AvField
                  id="avatar-category-path"
                  type="text"
                  name="path"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    maxLength: { value: 128, errorMessage: translate('entity.validation.maxlength', { max: 128 }) },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="nameLabel" for="avatar-category-name">
                  <Translate contentKey="nuriSocialApp.avatarCategory.name">Name</Translate>
                </Label>
                <AvField
                  id="avatar-category-name"
                  type="text"
                  name="name"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    maxLength: { value: 128, errorMessage: translate('entity.validation.maxlength', { max: 128 }) },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="iconLabel" for="avatar-category-icon">
                  <Translate contentKey="nuriSocialApp.avatarCategory.icon">Icon</Translate>
                </Label>
                <AvField
                  id="avatar-category-icon"
                  type="text"
                  name="icon"
                  validate={{
                    maxLength: { value: 1024, errorMessage: translate('entity.validation.maxlength', { max: 1024 }) },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="avatar-category-description">
                  <Translate contentKey="nuriSocialApp.avatarCategory.description">Description</Translate>
                </Label>
                <AvField
                  id="avatar-category-description"
                  type="text"
                  name="description"
                  validate={{
                    maxLength: { value: 1024, errorMessage: translate('entity.validation.maxlength', { max: 1024 }) },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="statusLabel" for="avatar-category-status">
                  <Translate contentKey="nuriSocialApp.avatarCategory.status">Status</Translate>
                </Label>
                <AvInput
                  id="avatar-category-status"
                  type="select"
                  className="form-control"
                  name="status"
                  value={(!isNew && avatarCategoryEntity.status) || 'ACTIVATED'}
                >
                  <option value="ACTIVATED">{translate('nuriSocialApp.CategoryStatus.ACTIVATED')}</option>
                  <option value="VALID">{translate('nuriSocialApp.CategoryStatus.VALID')}</option>
                  <option value="INVALID">{translate('nuriSocialApp.CategoryStatus.INVALID')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="createdLabel" for="avatar-category-created">
                  <Translate contentKey="nuriSocialApp.avatarCategory.created">Created</Translate>
                </Label>
                <AvInput
                  id="avatar-category-created"
                  type="datetime-local"
                  className="form-control"
                  name="created"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.avatarCategoryEntity.created)}
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="modifiedLabel" for="avatar-category-modified">
                  <Translate contentKey="nuriSocialApp.avatarCategory.modified">Modified</Translate>
                </Label>
                <AvInput
                  id="avatar-category-modified"
                  type="datetime-local"
                  className="form-control"
                  name="modified"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.avatarCategoryEntity.modified)}
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/avatar-category" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  avatarCategoryEntity: storeState.avatarCategory.entity,
  loading: storeState.avatarCategory.loading,
  updating: storeState.avatarCategory.updating,
  updateSuccess: storeState.avatarCategory.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AvatarCategoryUpdate);
