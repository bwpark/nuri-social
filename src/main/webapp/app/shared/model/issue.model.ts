import { Moment } from 'moment';
import { IIssueOption } from 'app/shared/model/issue-option.model';
import { IInteract } from 'app/shared/model/interact.model';
import { IIssueResource } from 'app/shared/model/issue-resource.model';
import { IEmotion } from 'app/shared/model/emotion.model';
import { IssueStatus } from 'app/shared/model/enumerations/issue-status.model';

export interface IIssue {
  id?: string;
  categoryName?: string;
  name?: string;
  description?: string;
  text?: any;
  coin?: number;
  point?: number;
  respect?: number;
  diss?: number;
  author?: string;
  views?: number;
  comments?: number;
  status?: IssueStatus;
  created?: string;
  modified?: string;
  options?: IIssueOption[];
  interacts?: IInteract[];
  resources?: IIssueResource[];
  emotions?: IEmotion[];
  categoryId?: string;
  meId?: string;
}

export const defaultValue: Readonly<IIssue> = {};
