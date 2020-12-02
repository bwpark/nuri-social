import { Moment } from 'moment';
import { EmotionStatus } from 'app/shared/model/enumerations/emotion-status.model';

export interface IEmotion {
  id?: string;
  status?: EmotionStatus;
  created?: string;
  modified?: string;
  youId?: string;
  issueId?: string;
  meId?: string;
}

export const defaultValue: Readonly<IEmotion> = {};
