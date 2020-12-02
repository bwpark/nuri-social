import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IAvatar } from 'app/shared/model/avatar.model';
import { getEntities as getAvatars } from 'app/entities/avatar/avatar.reducer';
import { getEntity, updateEntity, createEntity, reset } from './category.reducer';
import { ICategory } from 'app/shared/model/category.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ICategoryUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CategoryUpdate = (props: ICategoryUpdateProps) => {
  const [meId, setMeId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { categoryEntity, avatars, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/category');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getAvatars();
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
        ...categoryEntity,
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
          <h2 id="nuriSocialApp.category.home.createOrEditLabel">
            <Translate contentKey="nuriSocialApp.category.home.createOrEditLabel">Create or edit a Category</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : categoryEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="category-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="category-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="pathLabel" for="category-path">
                  <Translate contentKey="nuriSocialApp.category.path">Path</Translate>
                </Label>
                <AvField
                  id="category-path"
                  type="text"
                  name="path"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    maxLength: { value: 128, errorMessage: translate('entity.validation.maxlength', { max: 128 }) },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="nameLabel" for="category-name">
                  <Translate contentKey="nuriSocialApp.category.name">Name</Translate>
                </Label>
                <AvField
                  id="category-name"
                  type="text"
                  name="name"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    maxLength: { value: 128, errorMessage: translate('entity.validation.maxlength', { max: 128 }) },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="iconLabel" for="category-icon">
                  <Translate contentKey="nuriSocialApp.category.icon">Icon</Translate>
                </Label>
                <AvField
                  id="category-icon"
                  type="text"
                  name="icon"
                  validate={{
                    maxLength: { value: 1024, errorMessage: translate('entity.validation.maxlength', { max: 1024 }) },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="category-description">
                  <Translate contentKey="nuriSocialApp.category.description">Description</Translate>
                </Label>
                <AvField
                  id="category-description"
                  type="text"
                  name="description"
                  validate={{
                    maxLength: { value: 1024, errorMessage: translate('entity.validation.maxlength', { max: 1024 }) },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="hitAndSortLabel" for="category-hitAndSort">
                  <Translate contentKey="nuriSocialApp.category.hitAndSort">Hit And Sort</Translate>
                </Label>
                <AvField id="category-hitAndSort" type="string" className="form-control" name="hitAndSort" />
              </AvGroup>
              <AvGroup>
                <Label id="respectLabel" for="category-respect">
                  <Translate contentKey="nuriSocialApp.category.respect">Respect</Translate>
                </Label>
                <AvField id="category-respect" type="string" className="form-control" name="respect" />
              </AvGroup>
              <AvGroup>
                <Label id="dissLabel" for="category-diss">
                  <Translate contentKey="nuriSocialApp.category.diss">Diss</Translate>
                </Label>
                <AvField id="category-diss" type="string" className="form-control" name="diss" />
              </AvGroup>
              <AvGroup>
                <Label id="joinLabel" for="category-join">
                  <Translate contentKey="nuriSocialApp.category.join">Join</Translate>
                </Label>
                <AvField id="category-join" type="string" className="form-control" name="join" />
              </AvGroup>
              <AvGroup>
                <Label id="statusLabel" for="category-status">
                  <Translate contentKey="nuriSocialApp.category.status">Status</Translate>
                </Label>
                <AvInput
                  id="category-status"
                  type="select"
                  className="form-control"
                  name="status"
                  value={(!isNew && categoryEntity.status) || 'ACTIVATED'}
                >
                  <option value="ACTIVATED">{translate('nuriSocialApp.CategoryStatus.ACTIVATED')}</option>
                  <option value="VALID">{translate('nuriSocialApp.CategoryStatus.VALID')}</option>
                  <option value="INVALID">{translate('nuriSocialApp.CategoryStatus.INVALID')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="createdLabel" for="category-created">
                  <Translate contentKey="nuriSocialApp.category.created">Created</Translate>
                </Label>
                <AvInput
                  id="category-created"
                  type="datetime-local"
                  className="form-control"
                  name="created"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.categoryEntity.created)}
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="modifiedLabel" for="category-modified">
                  <Translate contentKey="nuriSocialApp.category.modified">Modified</Translate>
                </Label>
                <AvInput
                  id="category-modified"
                  type="datetime-local"
                  className="form-control"
                  name="modified"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.categoryEntity.modified)}
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label for="category-me">
                  <Translate contentKey="nuriSocialApp.category.me">Me</Translate>
                </Label>
                <AvInput id="category-me" type="select" className="form-control" name="meId">
                  <option value="" key="0" />
                  {avatars
                    ? avatars.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/category" replace color="info">
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
  avatars: storeState.avatar.entities,
  categoryEntity: storeState.category.entity,
  loading: storeState.category.loading,
  updating: storeState.category.updating,
  updateSuccess: storeState.category.updateSuccess,
});

const mapDispatchToProps = {
  getAvatars,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CategoryUpdate);
