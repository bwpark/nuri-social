import { Moment } from 'moment';
import { InteractStatus } from 'app/shared/model/enumerations/interact-status.model';

export interface IInteract {
  id?: string;
  text?: any;
  coin?: number;
  point?: number;
  respect?: number;
  diss?: number;
  author?: string;
  status?: InteractStatus;
  created?: string;
  modified?: string;
  children?: IInteract[];
  youId?: string;
  issueId?: string;
  parentId?: string;
  meId?: string;
}

export const defaultValue: Readonly<IInteract> = {};
