import { Moment } from 'moment';
import { IDealOption } from 'app/shared/model/deal-option.model';
import { DealStatus } from 'app/shared/model/enumerations/deal-status.model';

export interface IDeal {
  id?: string;
  name?: string;
  description?: string;
  quantity?: number;
  unitPrice?: number;
  coin?: number;
  point?: number;
  status?: DealStatus;
  created?: string;
  modified?: string;
  deals?: IDealOption[];
  withId?: string;
  packId?: string;
  toId?: string;
}

export const defaultValue: Readonly<IDeal> = {};
