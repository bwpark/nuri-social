import { Moment } from 'moment';
import { CategoryStatus } from 'app/shared/model/enumerations/category-status.model';

export interface IAvatarCategory {
  id?: string;
  path?: string;
  name?: string;
  icon?: string;
  description?: string;
  status?: CategoryStatus;
  created?: string;
  modified?: string;
}

export const defaultValue: Readonly<IAvatarCategory> = {};
