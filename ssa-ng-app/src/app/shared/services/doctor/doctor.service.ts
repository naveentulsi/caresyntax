import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ResultSetList } from '../../class/result-set-list';
import { UtilService } from '../util/util.service';

@Injectable({
  providedIn: 'root'
})
export class DoctorService {

  endpoint: string = UtilService.getServerUrl().concat('doc');
  constructor(private httpCliet: HttpClient) { }

  public getAllDoctors() {
    try {
      return this.httpCliet.get(this.endpoint);
    } catch (e) {
      return;
    }
  }
}
