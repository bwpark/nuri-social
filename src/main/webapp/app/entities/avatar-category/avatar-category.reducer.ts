import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IAvatarCategory, defaultValue } from 'app/shared/model/avatar-category.model';

export const ACTION_TYPES = {
  FETCH_AVATARCATEGORY_LIST: 'avatarCategory/FETCH_AVATARCATEGORY_LIST',
  FETCH_AVATARCATEGORY: 'avatarCategory/FETCH_AVATARCATEGORY',
  CREATE_AVATARCATEGORY: 'avatarCategory/CREATE_AVATARCATEGORY',
  UPDATE_AVATARCATEGORY: 'avatarCategory/UPDATE_AVATARCATEGORY',
  DELETE_AVATARCATEGORY: 'avatarCategory/DELETE_AVATARCATEGORY',
  RESET: 'avatarCategory/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IAvatarCategory>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type AvatarCategoryState = Readonly<typeof initialState>;

// Reducer

export default (state: AvatarCategoryState = initialState, action): AvatarCategoryState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_AVATARCATEGORY_LIST):
    case REQUEST(ACTION_TYPES.FETCH_AVATARCATEGORY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_AVATARCATEGORY):
    case REQUEST(ACTION_TYPES.UPDATE_AVATARCATEGORY):
    case REQUEST(ACTION_TYPES.DELETE_AVATARCATEGORY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_AVATARCATEGORY_LIST):
    case FAILURE(ACTION_TYPES.FETCH_AVATARCATEGORY):
    case FAILURE(ACTION_TYPES.CREATE_AVATARCATEGORY):
    case FAILURE(ACTION_TYPES.UPDATE_AVATARCATEGORY):
    case FAILURE(ACTION_TYPES.DELETE_AVATARCATEGORY):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_AVATARCATEGORY_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_AVATARCATEGORY):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_AVATARCATEGORY):
    case SUCCESS(ACTION_TYPES.UPDATE_AVATARCATEGORY):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_AVATARCATEGORY):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/avatar-categories';

// Actions

export const getEntities: ICrudGetAllAction<IAvatarCategory> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_AVATARCATEGORY_LIST,
  payload: axios.get<IAvatarCategory>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IAvatarCategory> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_AVATARCATEGORY,
    payload: axios.get<IAvatarCategory>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IAvatarCategory> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_AVATARCATEGORY,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IAvatarCategory> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_AVATARCATEGORY,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IAvatarCategory> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_AVATARCATEGORY,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
