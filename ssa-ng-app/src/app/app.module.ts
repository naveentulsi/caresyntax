import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddPatientComponent } from './routes/add-patient/add-patient.component';
import { RouterModule } from '@angular/router';
import { route } from './routes';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { PatientService } from './shared/services/patient/patient.service';
import { HttpClientModule } from '@angular/common/http';
import { ToastrModule } from 'ngx-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { StudySchedulerComponent } from './routes/study-scheduler/study-scheduler.component';
import { OwlDateTimeModule, OwlNativeDateTimeModule, OWL_DATE_TIME_FORMATS } from 'ng-pick-datetime';
import { DyanmicListingComponent } from './shared/components/dyanmic-listing/dyanmic-listing.component';
import { RoomService } from './shared/services/room/room.service';
import { DoctorService } from './shared/services/doctor/doctor.service';
import { RoomListingComponent } from './routes/room-listing/room-listing.component';
import { DoctorListingComponent } from './routes/doctor-listing/doctor-listing.component';
import { UpdateStudyComponent } from './routes/update-study/update-study.component';
import { PatientListingComponent } from './routes/patient-listing/patient-listing.component';

export const MY_MOMENT_FORMATS = {
  parseInput: 'LL LT',
  fullPickerInput: 'LL LT',
  datePickerInput: 'LL',
  timePickerInput: 'LT',
  monthYearLabel: 'MMM YYYY',
  dateA11yLabel: 'LL',
  monthYearA11yLabel: 'MMMM YYYY',
};

@NgModule({
  declarations: [
    AppComponent,
    AddPatientComponent,
    StudySchedulerComponent,
    DyanmicListingComponent,
    RoomListingComponent,
    DoctorListingComponent,
    UpdateStudyComponent,
    PatientListingComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    OwlDateTimeModule,
    OwlNativeDateTimeModule,
    ToastrModule.forRoot({
      positionClass: 'toast-top-right',
      preventDuplicates: true,
      maxOpened: 3
    }),
    RouterModule.forRoot(route, { useHash: true })
  ],
  providers: [PatientService, RoomService, DoctorService, HttpClientModule, {provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS}],
  bootstrap: [AppComponent]
})
export class AppModule { }
