import { Moment } from 'moment';
import { RegularStatus } from 'app/shared/model/enumerations/regular-status.model';

export interface IRegular {
  id?: string;
  name?: string;
  status?: RegularStatus;
  created?: string;
  modified?: string;
  youId?: string;
  meId?: string;
}

export const defaultValue: Readonly<IRegular> = {};
