import { Moment } from 'moment';
import { IDeal } from 'app/shared/model/deal.model';
import { PackStatus } from 'app/shared/model/enumerations/pack-status.model';

export interface IPack {
  id?: string;
  description?: string;
  coin?: number;
  point?: number;
  shipTo?: string;
  status?: PackStatus;
  created?: string;
  modified?: string;
  deals?: IDeal[];
  meId?: string;
}

export const defaultValue: Readonly<IPack> = {};
