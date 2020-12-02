import { Moment } from 'moment';
import { ResourceType } from 'app/shared/model/enumerations/resource-type.model';

export interface IIssueResource {
  id?: string;
  type?: ResourceType;
  link?: string;
  created?: string;
  issueId?: string;
}

export const defaultValue: Readonly<IIssueResource> = {};
