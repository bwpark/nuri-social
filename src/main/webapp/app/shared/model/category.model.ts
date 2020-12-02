import { Moment } from 'moment';
import { CategoryStatus } from 'app/shared/model/enumerations/category-status.model';

export interface ICategory {
  id?: string;
  path?: string;
  name?: string;
  icon?: string;
  description?: string;
  hitAndSort?: number;
  respect?: number;
  diss?: number;
  join?: number;
  status?: CategoryStatus;
  created?: string;
  modified?: string;
  meId?: string;
}

export const defaultValue: Readonly<ICategory> = {};
