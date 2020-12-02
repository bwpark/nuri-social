import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, setFileData, openFile, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IAvatarCategory } from 'app/shared/model/avatar-category.model';
import { getEntities as getAvatarCategories } from 'app/entities/avatar-category/avatar-category.reducer';
import { IUser } from 'app/shared/model/user.model';
import { getUsers } from 'app/modules/administration/user-management/user-management.reducer';
import { getEntity, updateEntity, createEntity, setBlob, reset } from './avatar.reducer';
import { IAvatar } from 'app/shared/model/avatar.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IAvatarUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AvatarUpdate = (props: IAvatarUpdateProps) => {
  const [categoryId, setCategoryId] = useState('0');
  const [userId, setUserId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { avatarEntity, avatarCategories, users, loading, updating } = props;

  const { text, logo, logoContentType, banner, bannerContentType } = avatarEntity;

  const handleClose = () => {
    props.history.push('/avatar');
  };

  useEffect(() => {
    if (!isNew) {
      props.getEntity(props.match.params.id);
    }

    props.getAvatarCategories();
    props.getUsers();
  }, []);

  const onBlobChange = (isAnImage, name) => event => {
    setFileData(event, (contentType, data) => props.setBlob(name, data, contentType), isAnImage);
  };

  const clearBlob = name => () => {
    props.setBlob(name, undefined, undefined);
  };

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
        ...avatarEntity,
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
          <h2 id="nuriSocialApp.avatar.home.createOrEditLabel">
            <Translate contentKey="nuriSocialApp.avatar.home.createOrEditLabel">Create or edit a Avatar</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : avatarEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="avatar-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="avatar-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="categoryNameLabel" for="avatar-categoryName">
                  <Translate contentKey="nuriSocialApp.avatar.categoryName">Category Name</Translate>
                </Label>
                <AvField
                  id="avatar-categoryName"
                  type="text"
                  name="categoryName"
                  validate={{
                    maxLength: { value: 128, errorMessage: translate('entity.validation.maxlength', { max: 128 }) },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="nameLabel" for="avatar-name">
                  <Translate contentKey="nuriSocialApp.avatar.name">Name</Translate>
                </Label>
                <AvField
                  id="avatar-name"
                  type="text"
                  name="name"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    maxLength: { value: 128, errorMessage: translate('entity.validation.maxlength', { max: 128 }) },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="avatar-description">
                  <Translate contentKey="nuriSocialApp.avatar.description">Description</Translate>
                </Label>
                <AvField
                  id="avatar-description"
                  type="text"
                  name="description"
                  validate={{
                    maxLength: { value: 1024, errorMessage: translate('entity.validation.maxlength', { max: 1024 }) },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="textLabel" for="avatar-text">
                  <Translate contentKey="nuriSocialApp.avatar.text">Text</Translate>
                </Label>
                <AvInput id="avatar-text" type="textarea" name="text" />
              </AvGroup>
              <AvGroup>
                <AvGroup>
                  <Label id="logoLabel" for="logo">
                    <Translate contentKey="nuriSocialApp.avatar.logo">Logo</Translate>
                  </Label>
                  <br />
                  {logo ? (
                    <div>
                      {logoContentType ? (
                        <a onClick={openFile(logoContentType, logo)}>
                          <img src={`data:${logoContentType};base64,${logo}`} style={{ maxHeight: '100px' }} />
                        </a>
                      ) : null}
                      <br />
                      <Row>
                        <Col md="11">
                          <span>
                            {logoContentType}, {byteSize(logo)}
                          </span>
                        </Col>
                        <Col md="1">
                          <Button color="danger" onClick={clearBlob('logo')}>
                            <FontAwesomeIcon icon="times-circle" />
                          </Button>
                        </Col>
                      </Row>
                    </div>
                  ) : null}
                  <input id="file_logo" type="file" onChange={onBlobChange(true, 'logo')} accept="image/*" />
                  <AvInput
                    type="hidden"
                    name="logo"
                    value={logo}
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                    }}
                  />
                </AvGroup>
              </AvGroup>
              <AvGroup>
                <AvGroup>
                  <Label id="bannerLabel" for="banner">
                    <Translate contentKey="nuriSocialApp.avatar.banner">Banner</Translate>
                  </Label>
                  <br />
                  {banner ? (
                    <div>
                      {bannerContentType ? (
                        <a onClick={openFile(bannerContentType, banner)}>
                          <img src={`data:${bannerContentType};base64,${banner}`} style={{ maxHeight: '100px' }} />
                        </a>
                      ) : null}
                      <br />
                      <Row>
                        <Col md="11">
                          <span>
                            {bannerContentType}, {byteSize(banner)}
                          </span>
                        </Col>
                        <Col md="1">
                          <Button color="danger" onClick={clearBlob('banner')}>
                            <FontAwesomeIcon icon="times-circle" />
                          </Button>
                        </Col>
                      </Row>
                    </div>
                  ) : null}
                  <input id="file_banner" type="file" onChange={onBlobChange(true, 'banner')} accept="image/*" />
                  <AvInput
                    type="hidden"
                    name="banner"
                    value={banner}
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                    }}
                  />
                </AvGroup>
              </AvGroup>
              <AvGroup>
                <Label id="coinLabel" for="avatar-coin">
                  <Translate contentKey="nuriSocialApp.avatar.coin">Coin</Translate>
                </Label>
                <AvField
                  id="avatar-coin"
                  type="string"
                  className="form-control"
                  name="coin"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    number: { value: true, errorMessage: translate('entity.validation.number') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="pointLabel" for="avatar-point">
                  <Translate contentKey="nuriSocialApp.avatar.point">Point</Translate>
                </Label>
                <AvField
                  id="avatar-point"
                  type="string"
                  className="form-control"
                  name="point"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    number: { value: true, errorMessage: translate('entity.validation.number') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="respectLabel" for="avatar-respect">
                  <Translate contentKey="nuriSocialApp.avatar.respect">Respect</Translate>
                </Label>
                <AvField
                  id="avatar-respect"
                  type="string"
                  className="form-control"
                  name="respect"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    number: { value: true, errorMessage: translate('entity.validation.number') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="dissLabel" for="avatar-diss">
                  <Translate contentKey="nuriSocialApp.avatar.diss">Diss</Translate>
                </Label>
                <AvField
                  id="avatar-diss"
                  type="string"
                  className="form-control"
                  name="diss"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    number: { value: true, errorMessage: translate('entity.validation.number') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="gradeLabel" for="avatar-grade">
                  <Translate contentKey="nuriSocialApp.avatar.grade">Grade</Translate>
                </Label>
                <AvField
                  id="avatar-grade"
                  type="string"
                  className="form-control"
                  name="grade"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    number: { value: true, errorMessage: translate('entity.validation.number') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="creditLabel" for="avatar-credit">
                  <Translate contentKey="nuriSocialApp.avatar.credit">Credit</Translate>
                </Label>
                <AvField
                  id="avatar-credit"
                  type="string"
                  className="form-control"
                  name="credit"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    number: { value: true, errorMessage: translate('entity.validation.number') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="viewsLabel" for="avatar-views">
                  <Translate contentKey="nuriSocialApp.avatar.views">Views</Translate>
                </Label>
                <AvField
                  id="avatar-views"
                  type="string"
                  className="form-control"
                  name="views"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    number: { value: true, errorMessage: translate('entity.validation.number') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="commentsLabel" for="avatar-comments">
                  <Translate contentKey="nuriSocialApp.avatar.comments">Comments</Translate>
                </Label>
                <AvField
                  id="avatar-comments"
                  type="string"
                  className="form-control"
                  name="comments"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    number: { value: true, errorMessage: translate('entity.validation.number') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="statusLabel" for="avatar-status">
                  <Translate contentKey="nuriSocialApp.avatar.status">Status</Translate>
                </Label>
                <AvInput
                  id="avatar-status"
                  type="select"
                  className="form-control"
                  name="status"
                  value={(!isNew && avatarEntity.status) || 'ACTIVATED'}
                >
                  <option value="ACTIVATED">{translate('nuriSocialApp.AvatarStatus.ACTIVATED')}</option>
                  <option value="VALID">{translate('nuriSocialApp.AvatarStatus.VALID')}</option>
                  <option value="INVALID">{translate('nuriSocialApp.AvatarStatus.INVALID')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="createdLabel" for="avatar-created">
                  <Translate contentKey="nuriSocialApp.avatar.created">Created</Translate>
                </Label>
                <AvInput
                  id="avatar-created"
                  type="datetime-local"
                  className="form-control"
                  name="created"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.avatarEntity.created)}
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="modifiedLabel" for="avatar-modified">
                  <Translate contentKey="nuriSocialApp.avatar.modified">Modified</Translate>
                </Label>
                <AvInput
                  id="avatar-modified"
                  type="datetime-local"
                  className="form-control"
                  name="modified"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.avatarEntity.modified)}
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label for="avatar-category">
                  <Translate contentKey="nuriSocialApp.avatar.category">Category</Translate>
                </Label>
                <AvInput id="avatar-category" type="select" className="form-control" name="categoryId">
                  <option value="" key="0" />
                  {avatarCategories
                    ? avatarCategories.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="avatar-user">
                  <Translate contentKey="nuriSocialApp.avatar.user">User</Translate>
                </Label>
                <AvInput id="avatar-user" type="select" className="form-control" name="userId">
                  <option value="" key="0" />
                  {users
                    ? users.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/avatar" replace color="info">
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
  avatarCategories: storeState.avatarCategory.entities,
  users: storeState.userManagement.users,
  avatarEntity: storeState.avatar.entity,
  loading: storeState.avatar.loading,
  updating: storeState.avatar.updating,
  updateSuccess: storeState.avatar.updateSuccess,
});

const mapDispatchToProps = {
  getAvatarCategories,
  getUsers,
  getEntity,
  updateEntity,
  setBlob,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AvatarUpdate);
