import { Route } from '@angular/router';
import { AddPatientComponent } from './routes/add-patient/add-patient.component';
import { AppComponent } from './app.component';
import { StudySchedulerComponent } from './routes/study-scheduler/study-scheduler.component';
import { RoomListingComponent } from './routes/room-listing/room-listing.component';
import { DoctorListingComponent } from './routes/doctor-listing/doctor-listing.component';
import { UpdateStudyComponent } from './routes/update-study/update-study.component';
import { PatientListingComponent } from './routes/patient-listing/patient-listing.component';

export const route: Route[] = [
  {
    path: 'ap',
    component: AddPatientComponent
  },
  {
    path: 'sc',
    component: StudySchedulerComponent
  },
  {
    path: 'rml',
    component: RoomListingComponent
  },
  {
    path: 'dcl',
    component: DoctorListingComponent
  },
  {
    path: 'ups',
    component: UpdateStudyComponent

  },
  {
    path: 'ptl',
    component: PatientListingComponent
  },
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'sc'
  }
];
