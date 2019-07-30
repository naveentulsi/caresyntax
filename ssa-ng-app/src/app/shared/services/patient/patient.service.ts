import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Patient } from 'src/app/shared/interfaces/patient/patient';
import { UtilService } from '../util/util.service';

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  endpoint: string = UtilService.getServerUrl().concat('patient');
  constructor(private httpCliet: HttpClient) { }

  public addPatient(patient: Patient) {
    try {
      return this.httpCliet.post(this.endpoint, patient);
    } catch (e) {
      return;
    }
  }

  public getAllPatient() {
    try {
      return this.httpCliet.get(this.endpoint);
    } catch (e) {
      return;
    }
  }

}
