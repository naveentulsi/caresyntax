import { Component, OnInit } from '@angular/core';
import { ToastService } from 'src/app/shared/services/toast/toast.service';
import { DoctorService } from 'src/app/shared/services/doctor/doctor.service';

@Component({
  selector: 'app-doctor-listing',
  templateUrl: './doctor-listing.component.html',
  styleUrls: ['./doctor-listing.component.css']
})
export class DoctorListingComponent implements OnInit {
  doctorList: any;
  constructor(private doctorService: DoctorService, private toastService: ToastService) { }

  ngOnInit() {
    const doctor$ = this.doctorService.getAllDoctors();
    doctor$.subscribe((res: any) => {
      try {
        this.doctorList = res.message;
      } catch (err) {
        this.showError(err);
      }
    }, (err) => {
      this.showError(err);
    }, () => {
    });
  }
  /**
     * Shoots error toast message
     * @param err error object to logged
     */
  private showError(err: any) {
    this.toastService.showError('Not able to fetch dcotors.');
    console.log(err);
  }
}
