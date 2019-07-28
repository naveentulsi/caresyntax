import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UtilService {

  constructor() { }

  static getServerUrl(): string {
    return 'http://localhost:8080/ssa/api/v1/';
  }
}
