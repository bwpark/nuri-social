import { Moment } from 'moment';

export interface IChemistry {
  id?: string;
  yourName?: string;
  toYou?: number;
  toMe?: number;
  created?: string;
  youId?: string;
  meId?: string;
}

export const defaultValue: Readonly<IChemistry> = {};
