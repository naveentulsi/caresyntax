import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UtilService {

  constructor() { }

  static getServerUrl(): string {
    return 'http://ec2-18-222-221-79.us-east-2.compute.amazonaws.com:8080/ssa/api/v1/';
    //return 'http://localhost:8080/ssa/api/v1/';
  }
}
