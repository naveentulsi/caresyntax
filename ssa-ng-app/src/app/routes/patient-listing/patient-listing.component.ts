import { Component, OnInit } from '@angular/core';
import { ToastService } from 'src/app/shared/services/toast/toast.service';
import { PatientService } from 'src/app/shared/services/patient/patient.service';

@Component({
  selector: 'app-patient-listing',
  templateUrl: './patient-listing.component.html',
  styleUrls: ['./patient-listing.component.css']
})
export class PatientListingComponent implements OnInit {
  patientList: any[];
  responseMessage: string;
  constructor(private toastService: ToastService, private patientService: PatientService) { }

  ngOnInit() {
    const patient$ = this.patientService.getAllPatient();
    patient$.subscribe((res: any) => {
      try {
        // check data obect in response
        if ('data' in res) {
          this.patientList = res.data;
        }
        // get response message
        if ('message' in res) {
          this.responseMessage = res.message;
        }
        // check errro obect in response
        if ('error' in res) {
          this.toastService.showError(this.responseMessage);
        }
      } catch (err) {
        this.toastService.showError('Unable to fetch patients.');
      }
    }, (err) => {
      this.toastService.showError('Unable to fetch patients.');
    }, () => {
    });
  }

}
