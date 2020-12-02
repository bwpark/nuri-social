import { Moment } from 'moment';
import { ReputeStatus } from 'app/shared/model/enumerations/repute-status.model';

export interface IRepute {
  id?: string;
  description?: string;
  grade?: number;
  credit?: number;
  status?: ReputeStatus;
  created?: string;
  modified?: string;
  youId?: string;
  meId?: string;
}

export const defaultValue: Readonly<IRepute> = {};
