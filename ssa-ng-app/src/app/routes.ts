import { Route } from '@angular/router';
import { AddPatientComponent } from './add-patient/add-patient.component';
import { AppComponent } from './app.component';

export const route: Route[] = [
  {
    path: 'addPatient',
    component: AddPatientComponent
  },
  {
    path: '*',
    component: AppComponent
  }
];
