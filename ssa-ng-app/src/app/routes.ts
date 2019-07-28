import { Route } from '@angular/router';
import { AddPatientComponent } from './routes/add-patient/add-patient.component';
import { AppComponent } from './app.component';
import { StudySchedulerComponent } from './routes/study-scheduler/study-scheduler.component';
import { RoomListingComponent } from './routes/room-listing/room-listing.component';
import { DoctorListingComponent } from './routes/doctor-listing/doctor-listing.component';

export const route: Route[] = [
  {
    path: 'addPatient',
    component: AddPatientComponent
  },
  {
    path: 'schedule',
    component: StudySchedulerComponent
  },
  {
    path: 'rl',
    component: RoomListingComponent
  },
  {
    path: 'dc',
    component: DoctorListingComponent
  },
  {
    path: '*',
    component: AppComponent
  }
];
