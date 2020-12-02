import { Moment } from 'moment';
import { DealOptionStatus } from 'app/shared/model/enumerations/deal-option-status.model';

export interface IDealOption {
  id?: string;
  name?: string;
  status?: DealOptionStatus;
  created?: string;
  modified?: string;
  packId?: string;
}

export const defaultValue: Readonly<IDealOption> = {};
