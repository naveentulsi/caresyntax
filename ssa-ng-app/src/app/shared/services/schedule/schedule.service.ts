import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UtilService } from '../util/util.service';
import { Procedure, ProcedureUpdate } from '../../interfaces/procedure/procedure';

@Injectable({
  providedIn: 'root'
})
export class ScheduleService {

  endpoint: string = UtilService.getServerUrl().concat('proc');
  constructor(private httpCliet: HttpClient) { }

  public scheduleProcdure(procedure: Procedure) {
    try {
      return this.httpCliet.post(this.endpoint, procedure);
    } catch (e) {
      return;
    }
  }

  public getAllProcedure() {
    try {
      return this.httpCliet.get(this.endpoint);
    } catch (e) {
      return;
    }
  }

  public updateProcedureStatus(procedureUpdate: ProcedureUpdate) {
    try {
      return this.httpCliet.put(this.endpoint, procedureUpdate);
    } catch (e) {
      return;
    }
  }

}
