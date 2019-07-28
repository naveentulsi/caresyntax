import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddPatientComponent } from './add-patient/add-patient.component';
import { RouterModule } from '@angular/router';
import { route } from './routes';
import { ReactiveFormsModule } from '@angular/forms';
import { PatientService } from './shared/services/patient/patient.service';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    AddPatientComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule.forRoot(route, {useHash: true})
  ],
  providers: [PatientService, HttpClientModule],
  bootstrap: [AppComponent]
})
export class AppModule { }
