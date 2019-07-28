import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Patient } from 'src/app/shared/intefaces/patient';

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  constructor(private httpCliet: HttpClient) { }

  public addPatient(patient: Patient) {
      return this.httpCliet.post('http://localhost:8080/ssa/api/v1/patient', patient);
  }

}
