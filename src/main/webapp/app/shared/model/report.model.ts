import { Moment } from 'moment';
import { ReportStatus } from 'app/shared/model/enumerations/report-status.model';

export interface IReport {
  id?: string;
  description?: string;
  name?: string;
  status?: ReportStatus;
  created?: string;
  modified?: string;
  youId?: string;
  meId?: string;
}

export const defaultValue: Readonly<IReport> = {};
