import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import AvatarCategory from './avatar-category';
import AvatarCategoryDetail from './avatar-category-detail';
import AvatarCategoryUpdate from './avatar-category-update';
import AvatarCategoryDeleteDialog from './avatar-category-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={AvatarCategoryUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={AvatarCategoryUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={AvatarCategoryDetail} />
      <ErrorBoundaryRoute path={match.url} component={AvatarCategory} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={AvatarCategoryDeleteDialog} />
  </>
);

export default Routes;
