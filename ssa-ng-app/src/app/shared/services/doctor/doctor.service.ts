import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ResultSetList } from '../../class/result-set-list';
import { UtilService } from '../util/util.service';

@Injectable({
  providedIn: 'root'
})
export class DoctorService {

  constructor(private httpCliet: HttpClient) { }

  public getAllDoctors() {
    const serverUrl = UtilService.getServerUrl();
    const endpoint = serverUrl.concat('doctor');
    return this.httpCliet.get(endpoint);
  }
}
