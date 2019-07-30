import { Component, OnInit } from '@angular/core';
import { ToastService } from 'src/app/shared/services/toast/toast.service';
import { DoctorService } from 'src/app/shared/services/doctor/doctor.service';

@Component({
  selector: 'app-doctor-listing',
  templateUrl: './doctor-listing.component.html',
  styleUrls: ['./doctor-listing.component.css']
})
export class DoctorListingComponent implements OnInit {
  doctorList: any[];
  responseMessage: string;
  constructor(private doctorService: DoctorService, private toastService: ToastService) { }

  ngOnInit() {
    const doctor$ = this.doctorService.getAllDoctors();
    doctor$.subscribe((res: any) => {
      try {
        // check data obect in response
        if ('data' in res) {
          this.doctorList = res.data;
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
      this.toastService.showError('Unable to fetch doctors.');
    }, () => {
    });
  }
}
